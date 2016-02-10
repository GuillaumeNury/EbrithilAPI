package ebrithilapi.areas.core;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpMessage;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientAPIFactory<T> {
	private CloseableHttpClient httpClient;
	private Class<T> resultType;
	
	public ClientAPIFactory(Class<T> resultType){
		this.resultType = resultType;
		this.httpClient = HttpClients.createDefault();
	}
	
	// GET
	public T doGet(String url) throws ClientProtocolException, IOException{
		return this.doGet(url, null);
	}
	
	public T doGet(String url, List<NameValuePair> headers) throws ClientProtocolException, IOException{
		HttpGet getMethod = new HttpGet(url);
		this.injectHeaders(getMethod, headers);		
		return this.getResultOfRequest(getMethod);
	}
	
	// POST
	public T doPost(String url) throws ParseException, IOException{
		return this.doPost(url, null, null);
	}
	
	public T doPost(String url, List<NameValuePair> data) throws ParseException, IOException{
		return this.doPost(url, null, data);
	}

	public T doPost(String url, List<NameValuePair> headers, List<NameValuePair> data) throws ParseException, IOException{
		HttpPost postMethod = new HttpPost(url);
		
		if(data != null && !data.isEmpty()){
			postMethod.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
		}
		
		this.injectHeaders(postMethod, headers);
		return this.getResultOfRequest(postMethod);
	}

	// CORE
	public void injectHeaders(HttpMessage method, List<NameValuePair> headers){
		if(headers != null && !headers.isEmpty()){
			for(NameValuePair pair : headers){
				method.addHeader(pair.getName(), pair.getValue());
			}
		}
	}
	
	public T getResultOfRequest(HttpUriRequest method) throws ClientProtocolException, IOException{
		CloseableHttpResponse response = this.httpClient.execute(method);
		String res = EntityUtils.toString(response.getEntity());
		
		ObjectMapper mapper = new ObjectMapper();
		T result = mapper.readValue(res, this.resultType);
		
		return result;
	}

}
