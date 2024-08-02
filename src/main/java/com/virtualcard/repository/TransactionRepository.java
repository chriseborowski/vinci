package com.virtualcard.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualcard.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByVirtualCardId(Long virtualCardId);
    List<Transaction> findByVirtualCardIdAndDateBetween(Long virtualCardId, LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> findByTransactionBetween(LocalDateTime start, LocalDateTime end);
}
