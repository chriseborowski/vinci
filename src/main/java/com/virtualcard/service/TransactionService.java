package com.virtualcard.service;

import com.virtualcard.model.Transaction;
import com.virtualcard.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  public Transaction createTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public Optional<Transaction> getTransactionById(Long id) {
    return transactionRepository.findById(id);
  }

  public List<Transaction> getTransactionsByCardId(Long cardId) {
    return transactionRepository.findByVirtualCardId(cardId);
  }

  public List<Transaction> getTransactionsByCardIdAndDateRange(Long cardId, LocalDateTime startDate, LocalDateTime endDate) {
    return transactionRepository.findByVirtualCardIdAndDateBetween(cardId, startDate, endDate);
  }

}
