package com.qa.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.qa.Data.Headers;

public class RestClient {
	Headers headerobj = new Headers();

	// 1. GET Method without Headers:
	public CloseableHttpResponse get(String url)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closebaleHttpResponse = httpClient
				.execute(httpget); // hit the GET URL

		return closebaleHttpResponse;

		// 1. GET Method with Headers:
	}

	public CloseableHttpResponse get(String Url, HashMap<String, String> header)
			throws ClientProtocolException, IOException {

		CloseableHttpClient closableHttp = HttpClients.createDefault();// Create//
																		// the//
																		// client
		HttpGet httpget = new HttpGet(Url);// Get the URL

		httpget.addHeader(headerobj.getKey(), headerobj.getValue());

		CloseableHttpResponse closebaleHttpResponse = closableHttp
				.execute(httpget);

		return closebaleHttpResponse;

	}

	// Post Method

	public CloseableHttpResponse post(String uri, String entityString,
			HashMap<String, String> headermap) throws ClientProtocolException,
			IOException {

		CloseableHttpClient httpClosable = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);// For URL

		httpPost.setEntity(new StringEntity(entityString));

		httpPost.addHeader(headerobj.getKey(), headerobj.getValue());
		CloseableHttpResponse closeableHttpResponse = httpClosable
				.execute(httpPost);

		return closeableHttpResponse;

	}

	// Put Method

	public CloseableHttpResponse put(String url, String entityString,
			HashMap<String, String> headerMap) throws ClientProtocolException,
			IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut putobj = new HttpPut(url);
		putobj.addHeader(headerobj.getKey(), headerobj.getValue());

		putobj.setEntity(new StringEntity(entityString));

		CloseableHttpResponse closeableHttpResponse = httpClient
				.execute(putobj);

		return closeableHttpResponse;
	}

}
