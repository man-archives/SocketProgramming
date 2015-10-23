import java.io.UnsupportedEncodingException;
import java.io.URLEncoder;

public class  QueryStringFormatter{
	private String queryEngine;
	private StringBuilder query = new StringBuilder();

	private QueryStringFormatter(String queryEngine){
		this.queryEngine = queryEngine;
	}

	public String getEngine(){
		return this.queryEngine;
	}

	public void addQuery(String queryString, String queryValue) throws Exception{
		query.append(queryString + "=" + URLEncoder.encode(queryValue,"UTF-8") + "&");
	}

	public String getQueryString(){
		return "?" + query.toString();
	}

	public static void main(String[] args){
		QueryStringFormatter formatter = new QueryStringFormatter("http://www.google.co.in/search");
		formatter.addQuery("newwindow","1");
		formatter.addQuery("q", "Man Parvesh Singh Randhawa");

		System.out.println(formatter.getEngine() + formatter.getQueryString());
	}
}