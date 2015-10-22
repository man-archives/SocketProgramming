import java.io.*;
import java.net.*;

//this class is to receive the broadcast from the server
public class SimpleClient{
	public static void main(String[] args) throws Exception{
		//firstly, opening a connection at the same port (8020)
		Socket socket = new Socket("localhost"/*What did you think? :P */, 8020);
		//imput stream
		InputStream in = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(in);
		//getting the string sent from server
		String input = new String(dataInputStream.readUTF());
		PrintWriter out = new PrintWriter(System.out);
		out.println(input); // hogya!

		//closing everything now
		out.close();
		dataInputStream.close();
		in.close();
		socket.close();
	}
}