package Wellner;

import java.util.Scanner;

public class HangmanProtocol {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tries = 3;

		String gameWord = "HalloWelt".toUpperCase();
		StringBuilder masked = new StringBuilder(gameWord.replaceAll(".", "_"));

		while (true) {
			System.out.println(tries + " tries left: " + masked);
			String input = sc.nextLine().toUpperCase();

			if (masked.equals(gameWord)) {
				System.out.println("You win!");
				break;
			}

			if (input.length() == 1) {
				char c = input.charAt(0);

				for (int i = 0; i < gameWord.length(); i++) {

					if (c == gameWord.charAt(i))
						masked.setCharAt(i, c);

				}

			}
			
			// input > 1... tries--;...
		}

	}

}
