package com.gustavofernandez.poker.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum CardColor {

  HEARTS("H"),
  SPADES("S"),
  CLUBS("C"),
  DIAMONDS("D");

  private String symbol;

  public static Optional<CardColor> fromSymbol(String symbol) {
    return Arrays.stream(values())
        .filter(cardValue -> cardValue.getSymbol().equals(symbol))
        .findFirst();
  }
}
