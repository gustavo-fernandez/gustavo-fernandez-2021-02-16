package com.gustavofernandez.poker.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

  private Player player1;
  private Player player2;

  public Optional<Player> getWinner() {
    int comparisonResult = player1.getHand().compareTo(player2.getHand());
    return comparisonResult == 0 ? empty() : comparisonResult > 0 ? of(player1) : of(player2);
  }

}
