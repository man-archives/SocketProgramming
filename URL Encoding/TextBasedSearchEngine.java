import java.io.*;
import java.net.*;

public class  TextBasedSearchEngine{
	private String searchEngine;

	public TextBasedSearchEngine(String searchEngine){
		this.searchEngine = searchEngine;
	}

	public void performSearch(String queryString){
		try{
			URL url = new URL(searchEngine);
			URLConnection connection  = url.openConnection();
			connection.setOutput(true);

			//query String gto search engine
			PrintStream ps = new PrintStream(connection.getOutputStream());
			ps.println(queryString);
			ps.close();

			//read karo hun
			DataInputStream input = new DataInputStream(connection.getInputStream());
			String inputLine = null;
			while(inputLine = input.readLine() != null){
				System.out.println(inputLine);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception{
		QueryStringFormatter formatter = new QueryStringFormatter("https://duckduckgo.com/search");
		formatter.addQuery("newwindow", "1");
		formatter.addQuery("q", "Man Parvesh Singh Randhawa");

		//perform search
		TextBasedSearchEngine search = new TextBasedSearchEngine(formatter.getEngine());
		search.performSearch(formatter.getQueryString());
	}
}