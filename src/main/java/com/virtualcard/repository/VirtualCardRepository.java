package com.virtualcard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualcard.model.VirtualCard;
import com.virtualcard.model.CardStatus;

@Repository
public interface VirtualCardRepository extends JpaRepository<VirtualCard, Long> {
    Optional<VirtualCard> findByCardNumber(String cardNumber);
    List<VirtualCard> findByUserId(Long userId);
    List<VirtualCard> findByStatus(CardStatus status);
}
