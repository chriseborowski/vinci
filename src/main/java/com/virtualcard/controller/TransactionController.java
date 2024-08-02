package com.virtualcard.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import com.virtualcard.model.Transaction;
import com.virtualcard.service.TransactionService;
import com.virtualcard.repository.TransactionRepository;
import com.virtualcard.repository.VirtualCardRepository;

import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  @Autowired
  private VirtualCardRepository virtualCardRepository;
  private TransactionRepository transactionRepository;
  private TransactionService transactionService;

  public List<Transaction> getTransactionsByCardAndDateRange(Long cardId, LocalDateTime startDate, LocalDateTime endDate) {
    virtualCardRepository.findById(cardId)
        .orElseThrow(() -> new RuntimeException("Virtual Card not found"));
    return transactionRepository.findByVirtualCardAndTransactionDateBetween(cardId, startDate, endDate);
  }

  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
    return ResponseEntity.ok(transactionService.createTransaction(transaction));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
    return transactionService.getTransactionById(id)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/card/{cardId}")
  public ResponseEntity<List<Transaction>> getCardTransactions(@PathVariable Long cardId) {
    return ResponseEntity.ok(transactionService.getTransactionsByCardId(cardId));
  }

  @GetMapping("/card/{cardId}/date-range")
  public ResponseEntity<List<Transaction>> getCardTransactionsByDateRange(
    @PathVariable Long cardId,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
      List<Transaction> transactions = transactionService.getTransactionsByCardIdAndDateRange(cardId, startDate, endDate);
      return ResponseEntity.ok(transactions);
    }

}
