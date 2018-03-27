package com.qa.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Data.Headers;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.JsonUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient= new RestClient();

	CloseableHttpResponse closebaleHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		// https://reqres.in/api/users

		url = serviceUrl + apiUrl;

	}

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException,
			IOException {
		
		closebaleHttpResponse = restClient.get(url);

		// a. Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,
				"Status code is not 200");

		// b.Json String

		String responseString = EntityUtils.toString(
				closebaleHttpResponse.getEntity(), "UTF-8");

		// Access JSON

		JSONObject responseJson = new JSONObject(responseString);

		System.out.println("JSON Response from API********>>>" + responseJson);
		// Validate jPath
		String perPageValue = JsonUtil
				.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is-->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3,
				"Per Page not Matched");

		String totalPage = JsonUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is-->" + totalPage);
		Assert.assertEquals(Integer.parseInt(totalPage), 12);

		// get the value from JSON ARRAY:
		String lastName = JsonUtil.getValueByJPath(responseJson,
				"/data[0]/last_name");
		String id = JsonUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = JsonUtil.getValueByJPath(responseJson,
				"/data[0]/avatar");
		String firstName = JsonUtil.getValueByJPath(responseJson,
				"/data[0]/first_name");
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);

		// Get all headers

		Header[] headerArray = closebaleHttpResponse.getAllHeaders();

		HashMap<String, String> headerMap = new HashMap<String, String>();

		for (Header h : headerArray) {
			headerMap.put(h.getName(), h.getValue());
		}
		System.out.println("************************headers********");

		System.out.println("key " + "  " + "Value");
		for (Map.Entry<String, String> map : headerMap.entrySet()) {

			System.out.println(map.getKey() + " " + map.getValue());
		}

	}

	@Test(priority = 2)
	public void getAPITestWithHeader() throws ClientProtocolException,
			IOException {

		CloseableHttpResponse httpResponse = restClient.get(url, Headers.getHeader());

		int responseCode = httpResponse.getStatusLine().getStatusCode();

		Assert.assertEquals(responseCode, testBase.RESPONSE_STATUS_CODE_200);
	}

}
