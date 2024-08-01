package com.virtualcard.model;

public enum CardStatus {
  ACTIVE,
  INACTIVE,
  BLOCKED,
  EXPIRED;

  public boolean readyToUse() {
    return this == ACTIVE;
  }

  public CardStatus block() {
    return this == ACTIVE ? BLOCKED : this;
  }

  public CardStatus unblock() {
    return this == BLOCKED ? ACTIVE : this;
  }

  public CardStatus expire() {
    return EXPIRED;
  }


}
