// implementation of the server

import java.io.*;
import java.net.*;

public class MathServer{
	protected MathService mathService;
	protected Socket socket;
	public static PrintWriter out = new PrintWriter(System.out);

	public void setMathService(MathService mathService){
		this.mathService = mathService;
	}

	public void setSocket(Socket socket){
		this.socket = socket;
	}

	public void execute(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			double result = parsingAndExcecution(br.readLine());

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write("" + result + "\n");
			bw.flush();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	protected double parsingAndExcecution(String s) throws IllegalArgumentException{
		double result = Double.MAX_VALUE;
		String elem[] = s.split(":"); // separating by colons
		if(elem.length != 3){
			throw new IllegalArgumentException("parse error.\nGalat no. of arguments");
		}
		double a = 0, b= 0;
		try{
			a = Double.parseDouble(elem[1]);
			b = Double.parseDouble(elem[2]);
		}catch(Exception e){
			throw new IllegalArgumentException("Invalid arguments.");
		}

		switch(elem[0].charAt(0)){
			case '+':
				result =  mathService.add(a,b);
				break;
			case '-':
				result =  mathService.subtract(a,b);
				break;
			case '*':
				result =  mathService.multiply(a,b);
				break;
			case '/':
				result =  mathService.divide(a,b);
				break;
			default:
				throw new IllegalArgumentException("Invalid math operation");
		}
		return result;
	}

	public static void main(String[] args) throws Exception{
		int port = 1000;
		if(args.length == 1){
			try{
				port = Integer.parseInt(args[0]);
			}catch(Exception e){

			}
		}
		out.println("Server running..");
		//create socket
		ServerSocket serverSocket = new Server(port);
		Socket socket = serverSocket.accept();

		MathServer mathServer = new MathServer();
		mathServer.setMathService(new PlainMathService());
		mathServer.setSocket(socket);
		mathServer.execute();
		out.println("server closing..");
		out.close();
	}
}