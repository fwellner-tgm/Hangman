package Wellner;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class HangmanProtocol {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tries = 3;
		ArrayList<String> words = new ArrayList<String>();
		Random random = new Random();

		try (Stream<String> stream = Files
				.lines(Paths.get("C:/Users/Florian/OneDrive/Workspace/Hangman/src/Wellner/words.txt"))) {
			stream.forEach(words::add);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String gameWord = words.get(random.nextInt(words.size())).toUpperCase();
		StringBuilder masked = new StringBuilder(gameWord.replaceAll(".", "_"));
		while (true) {

			if (tries == 0) {
				System.out.println("You lose!\nThe word was: " + gameWord);
				break;
			}

			System.out.println(tries + " tries left: " + masked);

			if (masked.toString().equals(gameWord)) {
				System.out.println("WIN!");
				break;
			}

			String input = sc.nextLine().toUpperCase();

			if (input.length() == 1) {
				char c = input.charAt(0);

				for (int i = 0; i < gameWord.length(); i++) {

					if (c == gameWord.charAt(i))
						masked.setCharAt(i, c);

				}

			} else if (input.length() > 1) {
				if (input.equals(gameWord)) {
					System.out.println("Word was: " + gameWord);
					System.out.println("You Win!");
					break;
				} else {
					tries--;
				}
			}

		}

	}

}
