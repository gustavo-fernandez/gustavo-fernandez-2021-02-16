package com.gustavofernandez.poker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum CardValue {

  ONE("1"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9"),
  TEN("T"),
  JACK("J"),
  QUEEN("Q"),
  KING("K"),
  ACE("A");

  private String symbol;

  public static Optional<CardValue> fromSymbol(String symbol) {
    return Arrays.stream(values())
        .filter(cardValue -> cardValue.getSymbol().equals(symbol))
        .findFirst();
  }

}
