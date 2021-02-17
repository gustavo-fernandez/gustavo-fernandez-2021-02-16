package com.gustavofernandez.poker.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

  private final List<Card> cards = new ArrayList<>(5);

  public Hand(List<Card> cards) {
    if (cards.size() != 5) {
      throw new RuntimeException("A hand must have 5 cards");
    }
    this.cards.addAll(cards);
  }

  public List<Card> getCards() {
    return List.copyOf(cards);
  }

  @Override
  public int compareTo(Hand another) {
    SpecialHand me = SpecialHand.fromHand(this);
    SpecialHand anotherSpecial = SpecialHand.fromHand(another);
    if (me.getHandRanking() != anotherSpecial.getHandRanking()) {
      return me.getHandRanking().ordinal() - anotherSpecial.getHandRanking().ordinal();
    }
    if (me.getHandRanking() == anotherSpecial.getHandRanking()
        && me.getValue() != anotherSpecial.getValue()) {
      return me.getValue() - anotherSpecial.getValue();
    }
    List<Integer> meNumbers = this.cards.stream()
        .map(Card::getNumber).map(Enum::ordinal)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    List<Integer> anotherNumbers = another.cards.stream()
        .map(Card::getNumber).map(Enum::ordinal)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    for (int i = meNumbers.size() - 1; i >= 0; i--) {
      int result = meNumbers.get(i) - anotherNumbers.get(i);
      if (result != 0) {
        return result;
      }
    }
    return 0;
  }
}
