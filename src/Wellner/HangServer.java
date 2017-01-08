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
		//String hostname = args[0];
		if (args.length != 1) {
			System.err.println("Usage: java HangServer <port number>");
			System.exit(1);
		}
		
		int portnr = Integer.parseInt(args[0]);
		
		try (
			ServerSocket ss = new ServerSocket(portnr);
			Socket cs = ss.accept();
			PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
		) {
			String input = "";
			String output = in.readLine();
			
			while (input != null) { 
				System.out.println("Client: " + output);
				
				System.out.println("Server: " + input);
				out.println(input);
			}
			
		} catch (IOException e) {
			System.err.println("Error");
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public void swag() {
		String gameWord = "HalloWelt";
		String masked = gameWord.replace("HalloWelt", "_");
	}
}