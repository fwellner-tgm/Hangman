package Wellner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HangClient {
	public static void main(String[] args) throws UnknownHostException, IOException {

		if (args.length != 2) {
			System.err.println("Usage: java HangClient <host name> <port number>");
			System.exit(1);
		}

		String hostname = args[0];
		int portnr = Integer.parseInt(args[1]);

		try (Socket socket = new Socket(hostname, portnr);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) 
		{
			Scanner scanner = new Scanner(System.in);
			String fromServer, fromUser;

			while ((fromServer = in.readLine()) != null) {
				if (fromServer.equals("win") || fromServer.equals("lose"))
					break;
				
				System.out.println("Server: " + fromServer);

				fromUser = scanner.nextLine();
				if (fromUser != null) {
					out.println(fromUser);
				}
			}
			
			System.out.println("Disconnected!");

		} catch (IOException e) {
			System.err.println("Error!");
			System.exit(1);
		}
	}
}