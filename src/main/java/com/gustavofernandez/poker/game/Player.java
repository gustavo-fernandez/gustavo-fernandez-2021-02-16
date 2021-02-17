package com.gustavofernandez.poker.game;

import com.gustavofernandez.poker.model.Hand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
  private int number;
  private Hand hand;
}
