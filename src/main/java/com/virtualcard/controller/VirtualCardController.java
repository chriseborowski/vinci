package com.virtualcard.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.virtualcard.service.VirtualCardService;
import com.virtualcard.model.VirtualCard;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class VirtualCardController {

  @Autowired
  private VirtualCardService virtualCardService;

  @PostMapping
  public ResponseEntity<VirtualCard> createCard(@RequestBody VirtualCard card) {
    return ResponseEntity.ok(virtualCardService.createCard(card));
  }

  @GetMapping("/{id}")
  public ResponseEntity<VirtualCard> getCard(@PathVariable Long id) {
    return virtualCardService.getCardById(id)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<VirtualCard>> getUserCards(@PathVariable Long userId) {
    return ResponseEntity.ok(virtualCardService.getCardsByUserId(userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<VirtualCard> updateCard(@PathVariable Long id, @RequestBody VirtualCard card) {
    card.setId(id);
    return ResponseEntity.ok(virtualCardService.updateCard(card));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
    virtualCardService.deleteCard(id);
    return ResponseEntity.noContent().build();
  }

}
