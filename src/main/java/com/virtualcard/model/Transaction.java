package com.virtualcard.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private LocalDateTime date;

  private String description;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "virtual_card_id")
  private VirtualCard virtualCard;

  // Constructors
  public Transaction() {
    // default constructor
  }

  public Transaction(Long id, BigDecimal amount, LocalDateTime date, String description, TransactionType type, VirtualCard virtualCard) {
    this.id = id;
    this.amount = amount;
    this.date = date;
    this.description = description;
    this.type = type;
    this.virtualCard = virtualCard;
  }

  // Setters and getters
  public long getId() {
    return id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }

  public TransactionType getType() {
    return type;
  }

  public VirtualCard getVirtualCard() {
    return virtualCard;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public void setVirtualCard(VirtualCard virtualCard) {
    this.virtualCard = virtualCard;
  }

}
