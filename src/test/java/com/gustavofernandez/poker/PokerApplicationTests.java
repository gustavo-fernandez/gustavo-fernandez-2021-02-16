package com.gustavofernandez.poker;

import com.gustavofernandez.poker.game.Game;
import com.gustavofernandez.poker.game.Player;
import com.gustavofernandez.poker.model.Hand;
import com.gustavofernandez.poker.util.HandsFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PokerApplicationTests {

	@Test
	void test() throws IOException {
		HandsFileReader handsFileReader = new HandsFileReader();
		List<Game> games = handsFileReader.readHandsFromFile(Paths.get("/Users/gustavo/Downloads/pokerdata.txt"));
		assertTrue(games.size() == 1_000);
	}

	@Test
	void evaluateGame() throws IOException {

	}

}
