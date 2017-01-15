package Wellner;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class HangmanProtocol {
	private Scanner sc = new Scanner(System.in);

	private int tries = 3;

	private ArrayList<String> words = new ArrayList<String>();

	private Random random = new Random();

	protected String gameWord;
	private StringBuilder masked;

	public HangmanProtocol() {
		try (Stream<String> stream = Files.lines(Paths.get("Wellner", "words.txt"))) {
			stream.forEach(words::add);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.gameWord = words.get(random.nextInt(words.size())).toUpperCase();
		this.masked = new StringBuilder(gameWord.replaceAll(".", "_"));
	}

	public String processInput(String input) {
		if (input != null) {
			input = input.toUpperCase();

			if (tries == 0) {
				return "lose";
				//break;
			}

			//System.out.println(tries + " tries left: " + masked);

			if (masked.toString().equals(gameWord)) {
				return "win";
			}

			if (input.length() == 1) {
				char c = input.charAt(0);

				for (int i = 0; i < gameWord.length(); i++) {

					if (c == gameWord.charAt(i)) {
						masked.setCharAt(i, c);
					}
				}

			} else if (input.length() > 1) {
				if (input.equals(gameWord)) {
					return "win";
					//break;
				} else {
					tries--;
				}
			}
		}
		return tries + " tries left: " + this.masked.toString();
	}
}
