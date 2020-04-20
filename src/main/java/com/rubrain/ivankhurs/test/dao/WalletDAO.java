package com.rubrain.ivankhurs.test.dao;

import com.rubrain.ivankhurs.test.model.entity.Wallet;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface provides data access methods
 * Based on Spring Data JPA
 * @see org.springframework.data.repository.CrudRepository
 */
public interface WalletDAO extends CrudRepository<Wallet, Long> {
}
