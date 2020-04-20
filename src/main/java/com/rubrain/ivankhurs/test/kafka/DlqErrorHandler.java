package com.rubrain.ivankhurs.test.kafka;

import com.rubrain.ivankhurs.test.service.exception.WalletServiceException;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerAwareErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class DlqErrorHandler implements ContainerAwareErrorHandler {

    public static final String DQL_TOPIC_NAME = "dlqTopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional("kafkaTransaction")
    public void handle(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer, MessageListenerContainer container) {
        ConsumerRecord<?, ?> record = records.get(0);
        try {
            if(thrownException instanceof WalletServiceException){
                logger.warn("Not enough funds for processing");
            }
            kafkaTemplate.send(DQL_TOPIC_NAME,  record.key().toString(), record.value().toString());
            consumer.seek(new TopicPartition(record.topic(), record.partition()), record.offset() + 1);
        } catch (Exception e) {
            consumer.seek(new TopicPartition(record.topic(), record.partition()), record.offset());
            throw new KafkaException("Seek to current after exception", thrownException);
        }
    }
}
