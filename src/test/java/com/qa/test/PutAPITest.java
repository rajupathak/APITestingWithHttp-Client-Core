package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Data.Headers;
import com.qa.Data.Users;
import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class PutAPITest {

	TestBase testbaseoj;
	String uri;
	RestClient restClient = new RestClient();

	@BeforeMethod
	public void setUp() {

		testbaseoj = new TestBase();
		String serviceUrl = testbaseoj.prop.getProperty("URL");

		String apirul = testbaseoj.prop.getProperty("serviceURL");
		uri = serviceUrl + apirul;
	}

	@Test
	public void putAPITest() throws ClientProtocolException, IOException {

		// To convert Java object to String we need JAKSON API Mashing and
		// UnMarshing

		ObjectMapper mapper = new ObjectMapper();

		Users userObject = new Users("Pathak", "AutomationTester");

		String entityObject = mapper.writeValueAsString(userObject);
		CloseableHttpResponse closeableHttpResponse = restClient.put(uri,entityObject, Headers.getHeader());

		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code Is : " +statusCode);

		Assert.assertEquals(statusCode, testbaseoj.RESPONSE_STATUS_CODE_200);

	}
	
	
	
}
