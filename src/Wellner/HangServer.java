package Wellner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HangServer {
	public static void main(String[] args) throws IOException {
		// String hostname = args[0];
		if (args.length != 1) {
			System.err.println("Usage: java HangServer <port number>");
			System.exit(1);
		}

		int portnr = Integer.parseInt(args[0]);

		try (ServerSocket ss = new ServerSocket(portnr);
				Socket cs = ss.accept();
				PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));) 
		{
			String inputLine, outputLine;

			HangmanProtocol hp = new HangmanProtocol();
			outputLine = hp.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				outputLine = hp.processInput(inputLine);
				
				if (outputLine.equals("win")) {
					out.println("You Win!");
					
				} else if (outputLine.equals("lose")) {
					out.println("You Lose! The word was: " + hp.gameWord);
					
				}
				out.println(outputLine);
			}

		} catch (IOException e) {
			System.err.println("Error");
			System.out.println(e.getMessage());
		}
	}
}