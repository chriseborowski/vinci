package com.virtualcard.service;

import com.virtualcard.model.VirtualCard;
import com.virtualcard.repository.VirtualCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VirtualCardService {

  @Autowired
  private VirtualCardRepository virtualCardRepository;

  public VirtualCard createCard(VirtualCard card) {
    return virtualCardRepository.save(card);
  }

  public Optional<VirtualCard> getCardById(Long id) {
    return virtualCardRepository.findById(id);
  }

  public List<VirtualCard> getCardsByUserId(Long userId) {
    return virtualCardRepository.findByUserId(userId);
  }

  public VirtualCard updateCard(VirtualCard card) {
    return virtualCardRepository.save(card);
  }

  public void deleteCard(Long id) {
    virtualCardRepository.deleteById(id);
  }

}
