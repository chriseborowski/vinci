package com.virtualcard.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  private String firstName;
  private String lastName;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VirtualCard> virtualCards = new ArrayList<>();

  // Constructors

  public User() {
    // default constructor
  }

  public User(String username, String password, String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String geFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<VirtualCard> getVirtualCards() {
    return virtualCards;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setVirtualCards(List<VirtualCard> virtualCards) {
    this.virtualCards = virtualCards;
  }

  // Helper methods for transactions
  public void addVirtualCard(VirtualCard virtualCard) {
    virtualCards.add(virtualCard);
    virtualCard.setUser(this);
  }

  public void removeVirtualCard(VirtualCard virtualCard) {
    virtualCards.remove(virtualCard);
    virtualCard.setUser(null);
  }

}
