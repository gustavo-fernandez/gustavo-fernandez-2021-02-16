package com.gustavofernandez.poker;

import com.gustavofernandez.poker.model.Card;
import com.gustavofernandez.poker.model.Hand;
import com.gustavofernandez.poker.model.SpecialHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavofernandez.poker.model.CardColor.*;
import static com.gustavofernandez.poker.model.CardValue.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {

  @Test
  void compareTo1() {
    Hand firstHand = new Hand(List.of(
        Card.builder().number(FIVE).color(HEARTS).build(),
        Card.builder().number(FIVE).color(CLUBS).build(),
        Card.builder().number(SIX).color(SPADES).build(),
        Card.builder().number(SEVEN).color(SPADES).build(),
        Card.builder().number(KING).color(DIAMONDS).build())
    );
    Hand secondHand = new Hand(List.of(
        Card.builder().number(TWO).color(CLUBS).build(),
        Card.builder().number(THREE).color(SPADES).build(),
        Card.builder().number(EIGHT).color(SPADES).build(),
        Card.builder().number(EIGHT).color(DIAMONDS).build(),
        Card.builder().number(THREE).color(DIAMONDS).build())
    );
    assertTrue(firstHand.compareTo(secondHand) < 0);
  }

  @Test
  void compareTo2() {
    Hand firstHand = new Hand(List.of(
        Card.builder().number(FIVE).color(DIAMONDS).build(),
        Card.builder().number(EIGHT).color(CLUBS).build(),
        Card.builder().number(NINE).color(SPADES).build(),
        Card.builder().number(JACK).color(SPADES).build(),
        Card.builder().number(ACE).color(CLUBS).build())
    );
    Hand secondHand = new Hand(List.of(
        Card.builder().number(TWO).color(CLUBS).build(),
        Card.builder().number(FIVE).color(CLUBS).build(),
        Card.builder().number(SEVEN).color(DIAMONDS).build(),
        Card.builder().number(EIGHT).color(SPADES).build(),
        Card.builder().number(QUEEN).color(HEARTS).build())
    );
    assertTrue(firstHand.compareTo(secondHand) > 0);
  }

  @Test
  void compareTo3() {
    Hand firstHand = new Hand(List.of(
        Card.builder().number(TWO).color(DIAMONDS).build(),
        Card.builder().number(NINE).color(CLUBS).build(),
        Card.builder().number(ACE).color(SPADES).build(),
        Card.builder().number(ACE).color(HEARTS).build(),
        Card.builder().number(ACE).color(CLUBS).build())
    );
    Hand secondHand = new Hand(List.of(
        Card.builder().number(THREE).color(DIAMONDS).build(),
        Card.builder().number(SIX).color(DIAMONDS).build(),
        Card.builder().number(SEVEN).color(DIAMONDS).build(),
        Card.builder().number(TEN).color(DIAMONDS).build(),
        Card.builder().number(QUEEN).color(DIAMONDS).build())
    );
    assertTrue(firstHand.compareTo(secondHand) < 0);
  }

  @Test
  void compareTo4() {
    Hand firstHand = new Hand(List.of(
        Card.builder().number(FOUR).color(DIAMONDS).build(),
        Card.builder().number(SIX).color(CLUBS).build(),
        Card.builder().number(NINE).color(HEARTS).build(),
        Card.builder().number(QUEEN).color(HEARTS).build(),
        Card.builder().number(QUEEN).color(CLUBS).build())
    );
    Hand secondHand = new Hand(List.of(
        Card.builder().number(THREE).color(DIAMONDS).build(),
        Card.builder().number(SIX).color(DIAMONDS).build(),
        Card.builder().number(SEVEN).color(HEARTS).build(),
        Card.builder().number(QUEEN).color(DIAMONDS).build(),
        Card.builder().number(QUEEN).color(SPADES).build())
    );
    assertTrue(firstHand.compareTo(secondHand) > 0);
  }

  @Test
  void compareTo5() {
    Hand firstHand = new Hand(List.of(
        Card.builder().number(TWO).color(HEARTS).build(),
        Card.builder().number(TWO).color(DIAMONDS).build(),
        Card.builder().number(FOUR).color(CLUBS).build(),
        Card.builder().number(FOUR).color(DIAMONDS).build(),
        Card.builder().number(FOUR).color(SPADES).build())
    );

    Hand secondHand = new Hand(List.of(
        Card.builder().number(THREE).color(CLUBS).build(),
        Card.builder().number(THREE).color(DIAMONDS).build(),
        Card.builder().number(THREE).color(SPADES).build(),
        Card.builder().number(NINE).color(SPADES).build(),
        Card.builder().number(NINE).color(DIAMONDS).build())
    );

    assertTrue(firstHand.compareTo(secondHand) > 0);
  }

}
