import java.io.*;
import java.net.*;

public class SimpleServer{
	public static void main(String[] args) throws Exception{
		ServerSocket serversocket = new ServerSocket(8020); // registering service on port 8020
		Socket socket = serversocket.accept(); // wait & accept a connection
		OutputStream serverOut = socket.getOutputStream(); // communication stream for selected socket
		DataOutputStream dataOutputStream = new DataOutputStream(serverOut);
		//let's send a string!
		dataOutputStream.writeUTF("Hello from server socket 8020\n");
		
		//done, close everything \m/
		//band kardo sab kuch!
		dataOutputStream.close();
		serverOut.close();
		socket.close();
	}
}