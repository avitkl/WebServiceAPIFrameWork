package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

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
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException
	{
		restClient = new RestClient();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson API -- Marshelling (Converting Java object to JSON)
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Avinash","Manager");
		
		//object to json file
		mapper.writeValue(new File("F:\\JavaSeleniumFramework\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//object to json in String
		String userJsonString=mapper.writeValueAsString(users);
		System.out.println("JSON String value --> "+userJsonString);
		
		closableHttpResponse=restClient.post(requesturl, userJsonString, headerMap);
		
		//a. Status Code :
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code --> "+statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
		
		//b. JsonString
		String responsString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");		
		JSONObject responseJson = new JSONObject(responsString);
		System.out.println("Response JSON from API --> "+responseJson);
		
		//json to java object -- Unmarshelling
		Users usersObj=mapper.readValue(responsString, Users.class); // actual users object
		
		Assert.assertTrue(users.getName().equals(usersObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersObj.getJob()));
	}
}
