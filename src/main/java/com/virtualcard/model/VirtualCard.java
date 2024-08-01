package com.virtualcard.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "virtual_cards")
public class VirtualCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private Long cardNumber;

  @Column(nullable = false)
  private LocalDate expirationDate;

  @Column(nullable = false)
  private int cvv;

  @Enumerated(EnumType.STRING)
  public CardStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "virtualCard", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<Transaction> transactions = new ArrayList<>();

  // Constructors

  public VirtualCard() {
    // default constructor
  }

  public VirtualCard(Long cardNumber, LocalDate expirationDate, int cvv, CardStatus status) {
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.cvv = cvv;
    this.status = status;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public Long getCardNumber() {
    return cardNumber;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public int getCvv() {
    return cvv;
  }

  public CardStatus getCardStatus() {
    return status;
  }

  public User getUser() {
    return user;
  }

  public List<Transaction> getTransactions() {
    return new ArrayList<>(transactions);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCardNumber(Long cardNumber) {
    this.cardNumber = cardNumber;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public void setCvv(int cvv) {
    this.cvv = cvv;
  }

  public void setStatus(CardStatus status) {
    this.status = status;
  }
  
  public void setUser(User user) {
    this.user = user;
  }

  //Helper methods for transactions
  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public void removeTransaction(Transaction transaction) {
    transactions.remove(transaction);
    transaction.setVirtualCard(null);
  }

  public static class Builder {

    private Long cardNumber;
    private LocalDate expirationDate;
    private int cvv;
    public CardStatus status;
    private User user;

    public Builder cardNumber(Long cardNumber) {
      this.cardNumber = cardNumber;
      return this;
    }

    public Builder expirationDate(LocalDate expirationDate) {
      this.expirationDate = expirationDate;
      return this;
    }

    public Builder cvv(int cvv) {
      this.cvv = cvv;
      return this;
    }

    public Builder status(CardStatus status) {
      this.status = status;
      return this;
    }

    public Builder user(User user) {
      this.user = user;
      return this;
    }

    public VirtualCard build() {
      VirtualCard virtualCard = new VirtualCard();
      virtualCard.setCardNumber(cardNumber);
      virtualCard.setExpirationDate(expirationDate);
      virtualCard.setCvv(cvv);
      virtualCard.setStatus(status);
      virtualCard.setUser(user);
      return virtualCard;
    }


  }

}
