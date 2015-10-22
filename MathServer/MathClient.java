import java.io.*;
import java.net.Socket;

public class MathClient{
	public static void main(String[] args){
		String host = "localhost";
		int port = 1000;
		PrintWriter out = new PrintWriter(System.out);
		if(args.length!=2){
			out.println("incorrect input..");
		}else{
			host = args[0];
			port = Integer.parseInt(args[1]);
		}

		try{
			//create socket
			Socket socket = new Socket(host, port);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write("+:12:21");
			bw.newLine();
			bw.flush();
			//time to get results \m/

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.prinltn(br.readLine());

			br.close();
			bw.close();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}