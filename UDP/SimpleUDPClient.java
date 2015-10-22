import java.io.*;
import java.net.*;

public class SimpleUDPClient{
	public static void main(String[] args){
		//socket banaya jaye!
		DatagramSocket dSocket = null;
		PrintWriter out = new PrintWriter(System.out);
		if(args.length < 3){
			//invalid input aguments on the command line
			out.println("22 chajj da kamm kar yar. It needs 3 arguments\nHow to use: java UDPClient <message> <Host name> <Port number>");
			out.close;
			System.exit(1); // bad command line aguments
		}
		try{
			dSocket = new DatagramSocket(socketNum); // nava socket datagram declare hogya!
			byte[] m = args[0].getBytes();
			InetAddress host = InetAddress.getByName(args[1]); 
			int port = Integer.valuOf(args[2]).intValue(); // server port

			DatagramPacket request = new DatagramPacket(m, args[0].length(), host, port);
			dSocket.send(request);

			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			dSocket.receive(reply);
			out.println("Reply aya hai ji:\n" + (new String(reply.getData())));
		}
		catch(SocketException se){
			out.println("SocketException ayi hai yar: " + se.getMessage());
		}
		catch (IOException e){
			out.println("IOException ayi hai: " + e.getMessage());
		}

		finally{
			if(dSocket != null){
				dSocket.close();
			}
		}

		out.close();
	}
}