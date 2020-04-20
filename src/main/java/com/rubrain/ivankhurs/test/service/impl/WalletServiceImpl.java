package com.rubrain.ivankhurs.test.service.impl;

import com.rubrain.ivankhurs.test.dao.WalletDAO;
import com.rubrain.ivankhurs.test.model.dto.WalletDTO;
import com.rubrain.ivankhurs.test.model.entity.Wallet;
import com.rubrain.ivankhurs.test.model.mapper.WalletMapper;
import com.rubrain.ivankhurs.test.service.WalletService;
import com.rubrain.ivankhurs.test.service.exception.WalletServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDAO walletDAO;

    @Autowired
    private WalletMapper walletMapper;

    @Override
    @Transactional("jpaTransaction")
    public void recharge(long walletId, long value) {
        Wallet wallet = getById(walletId);
        wallet.setBalance(wallet.getBalance() + value);
    }


    @Override
    @Transactional("jpaTransaction")
    public void withdraw(long walletId, long value) {
        Wallet wallet = getById(walletId);
        if (wallet.getBalance() - value >= 0) {
            wallet.setBalance(wallet.getBalance() - value);
        } else {
            throw new WalletServiceException("Not enough funds for processing");
        }
    }

    @Override
    @Transactional(value = "jpaTransaction", readOnly = true)
    public WalletDTO getWalletById(long walletId) {
        return walletMapper.toDTO(getById(walletId));
    }

    private Wallet getById(long walletId) {
        Optional<Wallet> walletOptional = walletDAO.findById(walletId);
        return walletOptional.orElse(null);
    }
}
