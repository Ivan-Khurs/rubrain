package com.rubrain.ivankhurs.test.kafka;

import com.rubrain.ivankhurs.test.model.dto.TransactionDTO;
import com.rubrain.ivankhurs.test.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KafkaProcessor {

    public static final String WALLET_TOPIC_NAME = "wallet";

    @Autowired
    private WalletService walletService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static final String RECHARGE_ACTION = "recharge";
    public static final String WITHDRAW_ACTION = "withdraw";

    @KafkaListener(topics = "transaction")
    @Transactional(transactionManager = "kafkaTransaction")
    public void listen(TransactionDTO transactionDTO) {
        if (transactionDTO.getAction().equals(RECHARGE_ACTION)) {
            walletService.recharge(transactionDTO.getWalletId(), transactionDTO.getValue());
        } else if (transactionDTO.getAction().equals(WITHDRAW_ACTION)) {
            walletService.withdraw(transactionDTO.getWalletId(), transactionDTO.getValue());
        }
        success(transactionDTO.toString());
    }

    public void success(String message) {
        kafkaTemplate.send(WALLET_TOPIC_NAME, message);
    }

}
