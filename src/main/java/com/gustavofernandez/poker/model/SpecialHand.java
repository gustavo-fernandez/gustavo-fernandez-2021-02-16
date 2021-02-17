package com.gustavofernandez.poker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.gustavofernandez.poker.model.CardValue.*;
import static com.gustavofernandez.poker.model.HandRanking.*;

@Getter
@AllArgsConstructor
public class SpecialHand {

  private HandRanking handRanking;
  private int value;

  public static SpecialHand fromHand(Hand hand) {
    Optional<SpecialHand> royalFlush = isRoyalFlush(hand);
    if (royalFlush.isPresent()) {
      return royalFlush.get();
    }
    Optional<SpecialHand> straightFlush = isStraightFlush(hand);
    if (straightFlush.isPresent()) {
      return straightFlush.get();
    }
    Optional<SpecialHand> fourOfAKind = isFourOfAKind(hand);
    if (fourOfAKind.isPresent()) {
      return fourOfAKind.get();
    }
    Optional<SpecialHand> fullHouse = isFullHouse(hand);
    if (fullHouse.isPresent()) {
      return fullHouse.get();
    }
    Optional<SpecialHand> flush = isFlush(hand);
    if (flush.isPresent()) {
      return flush.get();
    }
    Optional<SpecialHand> straight = isStraight(hand);
    if (straight.isPresent()) {
      return straight.get();
    }
    Optional<SpecialHand> threeOfAKind = isThreeOfAKind(hand);
    if (threeOfAKind.isPresent()) {
      return threeOfAKind.get();
    }
    Optional<SpecialHand> twoPairs = isTwoPairs(hand);
    if (twoPairs.isPresent()) {
      return twoPairs.get();
    }
    Optional<SpecialHand> onePair = isOnePair(hand);
    if (onePair.isPresent()) {
      return onePair.get();
    }
    return new SpecialHand(HIGH_CARD, 0);
  }

  private static Optional<SpecialHand> isRoyalFlush(Hand hand) {
    List<CardValue> royalFlushValues = List.of(TEN, JACK, QUEEN, KING, ACE);
    List<CardValue> cardValues = hand.getCards().stream().map(Card::getNumber).collect(Collectors.toList());
    boolean isRoyalFlush = cardValues.containsAll(royalFlushValues);
    return isRoyalFlush
        ? Optional.of(new SpecialHand(ROYAL_FLUSH, 0))
        : Optional.empty();
  }

  private static Optional<SpecialHand> isStraightFlush(Hand hand) {
    Optional<SpecialHand> flush = isFlush(hand);
    Optional<SpecialHand> straight = isStraight(hand);
    boolean isStraightFlush = flush.isPresent() && straight.isPresent();
    return isStraightFlush
        ? Optional.of(new SpecialHand(STRAIGHT_FLUSH, Math.max(flush.get().value, straight.get().value)))
        : Optional.empty();
  }

  private static Optional<SpecialHand> isFourOfAKind(Hand hand) {
    Map<CardValue, Long> count = hand.getCards().stream().map(Card::getNumber)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Optional<Map.Entry<CardValue, Long>> first = count.entrySet().stream().filter(entry -> entry.getValue().longValue() == 4L).findFirst();
    boolean hasFourOfAKind = first.isPresent();
    return hasFourOfAKind
        ? Optional.of(new SpecialHand(FOUR_OF_A_KIND, first.get().getKey().ordinal()))
        : Optional.empty();
  }

  private static Optional<SpecialHand> isFullHouse(Hand hand) {
    Optional<SpecialHand> onePair = isOnePair(hand);
    Optional<SpecialHand> threeOfAKind = isThreeOfAKind(hand);
    boolean isFullHouse = onePair.isPresent() && threeOfAKind.isPresent();
    return isFullHouse
        ? Optional.of(new SpecialHand(FULL_HOUSE, threeOfAKind.get().value))
        : Optional.empty();
  }

  private static Optional<SpecialHand> isFlush(Hand hand) {
    Set<CardColor> uniqueCardColors = hand.getCards().stream().map(Card::getColor).collect(Collectors.toSet());
    boolean allCardHaveTheSameColor = uniqueCardColors.size() == 1;
    return allCardHaveTheSameColor
        ? Optional.of(new SpecialHand(FLUSH, 0))
        : Optional.empty();
  }

  private static Optional<SpecialHand> isStraight(Hand hand) {
    List<Integer> cardNumbers = hand.getCards().stream()
        .map(Card::getNumber)
        .map(CardValue::ordinal)
        .sorted()
        .collect(Collectors.toList());
    for (int i = 0; i < cardNumbers.size() - 1; i++) {
      int current = cardNumbers.get(i);
      int next = cardNumbers.get(i + 1);
      if (next - current != 1) {
        return Optional.empty();
      }
    }
    return Optional.of(new SpecialHand(STRAIGHT, 0));
  }

  private static Optional<SpecialHand> isTwoPairs(Hand hand) {
    Map<CardValue, Long> count = hand.getCards().stream().map(Card::getNumber)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    List<Map.Entry<CardValue, Long>> list = count.entrySet().stream().filter(entry -> entry.getValue().longValue() == 2L).collect(Collectors.toList());
    boolean isTwoPairs = list.size() == 2;
    return isTwoPairs
        ? Optional.of(new SpecialHand(TWO_PAIRS, list.stream().map(Map.Entry::getKey).map(Enum::ordinal).sorted().findFirst().get()))
        : Optional.empty();
  }

  private static Optional<SpecialHand> isOnePair(Hand hand) {
    Map<CardValue, Long> count = hand.getCards().stream().map(Card::getNumber)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    List<Map.Entry<CardValue, Long>> list = count.entrySet().stream().filter(entry -> entry.getValue().longValue() == 2L).collect(Collectors.toList());
    boolean isOnePair = list.size() == 1;
    return isOnePair
        ? Optional.of(new SpecialHand(ONE_PAIR, list.get(0).getKey().ordinal()))
        : Optional.empty();
  }

  private static Optional<SpecialHand> isThreeOfAKind(Hand hand) {
    Map<CardValue, Long> count = hand.getCards().stream().map(Card::getNumber)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Optional<Map.Entry<CardValue, Long>> first = count.entrySet().stream().filter(entry -> entry.getValue().longValue() == 3L).findFirst();
    boolean isThreeOfAKind = first.isPresent();
    return isThreeOfAKind
        ? Optional.of(new SpecialHand(THREE_OF_A_KIND, first.get().getKey().ordinal()))
        : Optional.empty();
  }
}
