import java.io.*;
import java.net.*;

public class SimpleUDPServer{
	public static void main(String[] args){
		//socket banaya jaye!
		DatagramSocket dSocket = null;
		PrintWriter out = new PrintWriter(System.out);
		if(args.length < 1){
			//invalid input aguments on the command line
			out.println("22 chajj da kamm kar yar");
			out.close;
			System.exit(1); // bad command line aguments
		}
		try{
			int socketNum = Integer.valueOf(args[0]).intValue();
			dSocket = new DatagramSocket(socketNum); // nava socket datagram declare hogya!
			byte[] buffer = new byte[100];
			while(true){
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				dSocket.receive(request);

				DatagramPacket reply = new DatagramPacket(request.getData(), request.length(), request.getAddress(), request.getPort());
				dSocket.send(reply);
			}
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