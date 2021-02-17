package com.gustavofernandez.poker;

import com.gustavofernandez.poker.game.Game;
import com.gustavofernandez.poker.game.Player;
import com.gustavofernandez.poker.util.HandsFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class PokerApplication {

	public static void main(String[] args) throws IOException {
		try (ConfigurableApplicationContext context = SpringApplication.run(PokerApplication.class, args)) {
			String inputFilePath = context.getEnvironment().getProperty("input.file");
			HandsFileReader handsFileReader = new HandsFileReader();
			List<Game> games = handsFileReader.readHandsFromFile(Paths.get(inputFilePath));
			List<Optional<Player>> gameResults = games.stream()
					.map(Game::getWinner)
					.collect(Collectors.toList());
			long neitherPlayerWin = gameResults.stream().filter(Optional::isEmpty).count();
			long player1 = gameResults.stream()
					.filter(Optional::isPresent)
					.map(Optional::get)
					.filter(p -> p.getNumber() == 1)
					.count();
			long player2 = gameResults.stream()
					.filter(Optional::isPresent)
					.map(Optional::get)
					.filter(p -> p.getNumber() == 2)
					.count();
			StringBuilder result = new StringBuilder();
			result.append("Neither player win: " + neitherPlayerWin + "\n");
			result.append("Player 1 win: " + player1 + "\n");
			result.append("Player 2 win: " + player2 + "\n");
			Files.write(Paths.get("./result.txt"), result.toString().getBytes());
		}
	}

}
