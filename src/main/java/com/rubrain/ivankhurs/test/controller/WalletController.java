package com.rubrain.ivankhurs.test.controller;

import com.rubrain.ivankhurs.test.model.dto.WalletDTO;
import com.rubrain.ivankhurs.test.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The main controller with public APIs
 */
@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    /**
     * Method provides information about wallet balance
     * @param walletId - A wallet ID in a database
     * @return A WalletDTO json representative
     */
    @GetMapping("{walletId}/balance")
    public WalletDTO getBalance(@PathVariable("walletId") Long walletId) {
        return walletService.getWalletById(walletId);
    }
}
