package com.gustavofernandez.poker.util;

import com.gustavofernandez.poker.game.Game;
import com.gustavofernandez.poker.game.Player;
import com.gustavofernandez.poker.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HandsFileReader {

  public List<Game> readHandsFromFile(Path filePath) throws IOException {
    return Files.lines(filePath)
        .map(this::lineToGame)
        .collect(Collectors.toList());
  }

  private Game lineToGame(String line) {
    String[] parts = line.split(" ");
    String[] player1Part = Arrays.copyOfRange(parts, 0, 5);
    Hand handPlayer1 = partToHand(player1Part);
    String[] player2Part = Arrays.copyOfRange(parts, 5, 10);
    Hand handPlayer2 = partToHand(player2Part);
    return Game.builder()
        .player1(Player.builder().hand(handPlayer1).number(1).build())
        .player2(Player.builder().hand(handPlayer2).number(2).build())
        .build();
  }

  private Hand partToHand(String[] parts) {
    if (parts.length != 5) {
      throw new IllegalArgumentException("Part must have 5 elements");
    }
    List<Card> cards = new ArrayList<>(5);
    for (String part : parts) {
      String[] split = part.split("");
      String value = split[0];
      CardValue cardValue = CardValue.fromSymbol(value).orElseThrow(IllegalArgumentException::new);
      String color = split[1];
      CardColor cardColor = CardColor.fromSymbol(color).orElseThrow(IllegalArgumentException::new);
      cards.add(Card.builder().color(cardColor).number(cardValue).build());
    }
    return new Hand(cards);
  }

}
