package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
	
	private BufferedReader in;
	private PrintWriter out;
	//public enum Commands {} TODO: make enumeration for all commands
	
	
	public ClientMain(String cmd,String Domain, String URI, int Port, String Version) throws IOException {
		run(cmd, Domain, URI, Port, Version);
	}
	
	public void run(String cmd, String Domain, String URI, int Port, String Version) throws IOException {
		@SuppressWarnings("resource")
		Socket socket = new Socket(Domain, Port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		if (cmd.equals("GET")) {
			out.println(cmd + " " + URI + " " + Version + "\r\n");
			
			  StringBuilder sb = new StringBuilder();
		        String line = in.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append(System.lineSeparator());
		            line = in.readLine();
		        }
		        String response = sb.toString();
				System.out.print(response);

			/*String response;
			while( (response = in.readLine())!= null) {
				System.out.print(response);
			} */
		} else if (cmd.equals("POST")) {
			
			
		} else if (cmd.equals("HEAD")) {
			
		} else if (cmd.equals("PUT")) {
			
		} else {
			System.out.println("An error occured.");
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		String HTTPCommand = "";
		String URI = "";
		String Domain = "";
		int Port = 80;
		String HTTPVersion = "";
		
		if (args.length != 3) {
			System.out.println("Invalid argument count supplied.");
			System.out.println("Provide command, URI:port, and HTTP Version.");
			return;
		} else {
			HTTPCommand = args[0];
			URI = args[1];
			HTTPVersion = args[2];
			
			if (URI.startsWith("http://")) 
				URI = URI.substring(7);
			
			Domain = URI.split("/", 2)[0];
			URI = URI.split("/",2)[1];
			Port = Integer.parseInt(URI.split(":")[1]);
			URI = "/" + URI.split(":")[0];
			
		
			
			if (!HTTPCommand.equals("POST") && !HTTPCommand.equals("HEAD") && !HTTPCommand.equals("GET") && !HTTPCommand.equals("PUT")) {
				System.out.println("Invalid command.");
				return; 
			}
		}
			
		System.out.println("HTTP Client started.");
		ClientMain client = new ClientMain(HTTPCommand, Domain, URI, Port, HTTPVersion);
	}
	
	
	
}
