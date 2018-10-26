package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{
	
	TestBase testBase;
	String serviceurl;
	String apiUrl;
	String requesturl;
	RestClient restClient;
	CloseableHttpResponse closableHttpResponse;
	
	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		serviceurl=prop.getProperty("URL");
		apiUrl =prop.getProperty("serviceURL");		
		requesturl = serviceurl+apiUrl;	
	}
	
	@Test
	public void getUsersWithoutHeaders() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		closableHttpResponse=restClient.get(requesturl);
		
		//a. Status Code :
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code --> "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
		//b. Json String
		String responsString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");		
		JSONObject responseJson = new JSONObject(responsString);
		System.out.println("Response JSON from API --> "+responseJson);
		
		//single value assertion
		//per_page
		String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
		System.out.println("Value of perPage --> "+perPageValue);		
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total
		String totalValue = TestUtil.getValueByJPath(responseJson,"/total");
		System.out.println("Value of total is --> "+totalValue);		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from JSON ARRAY
		String lastName=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("Last name is --> "+lastName);
		System.out.println("ID name is --> "+id);
		System.out.println("Avatar is --> "+avatar);
		System.out.println("First name is --> "+firstName);
		
		Assert.assertEquals(lastName, "Bluth");
		Assert.assertEquals(Integer.parseInt(id), 1);
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		Assert.assertEquals(firstName, "George");
		
		//c. All Headers
		Header[] headersArray=closableHttpResponse.getAllHeaders();	
		HashMap<String,String> allHeaders = new HashMap<String,String>(); 	
		for(Header header : headersArray)
		 {
			allHeaders.put(header.getName(), header.getValue());
		 }		
		System.out.println("Headers Array --> " + allHeaders);
	}
	
	
	@Test
	public void getUsersWithHeaders() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		closableHttpResponse=restClient.get(requesturl,headerMap);
		
		//a. Status Code :
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code --> "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
		//b. Json String
		String responsString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");		
		JSONObject responseJson = new JSONObject(responsString);
		System.out.println("Response JSON from API --> "+responseJson);
		
		//single value assertion
		//per_page
		String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
		System.out.println("Value of perPage --> "+perPageValue);		
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total
		String totalValue = TestUtil.getValueByJPath(responseJson,"/total");
		System.out.println("Value of total is --> "+totalValue);		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from JSON ARRAY
		String lastName=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("Last name is --> "+lastName);
		System.out.println("ID name is --> "+id);
		System.out.println("Avatar is --> "+avatar);
		System.out.println("First name is --> "+firstName);
		
		Assert.assertEquals(lastName, "Bluth");
		Assert.assertEquals(Integer.parseInt(id), 1);
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		Assert.assertEquals(firstName, "George");
		
		//c. All Headers
		Header[] headersArray=closableHttpResponse.getAllHeaders();	
		HashMap<String,String> allHeaders = new HashMap<String,String>(); 	
		for(Header header : headersArray)
		 {
			allHeaders.put(header.getName(), header.getValue());
		 }		
		System.out.println("Headers Array --> " + allHeaders);
	}
	
	

}
