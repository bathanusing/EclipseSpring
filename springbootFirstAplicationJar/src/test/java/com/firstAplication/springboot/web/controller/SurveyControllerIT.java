package com.firstAplication.springboot.web.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;

import com.firstAplication.springboot.web.SpringbootFirstAplicationJarApplication;
import com.firstAplication.springboot.web.model.Question;

@SpringBootTest(classes = SpringbootFirstAplicationJarApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyControllerIT {
	// Se asigna un puerto al azar con webEnvironment y luego se injecta el valor en
	// esa variable para mostrarla
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@BeforeEach
	public void before() {
		headers.add("Authorization", createHttpAuthenticationHeaderValue("jean", "dummy"));

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	private String createHttpAuthenticationHeaderValue(String userId, String password) {

		String auth = userId + ":" + password;

		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));

		String headerValue = "Basic " + new String(encodedAuth);

		return headerValue;
	}

	@Test
	public void testRetrieveSurveyQuestion() throws JSONException {

		String url = CreateURL("/surveys/Survey1/questions/Question1");

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String CreateURL(String endPoint) {
		return "http://localhost:" + port + endPoint;
	}

	@Test
	public void testRetrieveAllSurveyQuestion() throws JSONException {

		String url = CreateURL("/surveys/Survey1/questions");

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String expected = "[\r\n" + "    {\r\n" + "        \"id\": \"Question1\",\r\n"
				+ "        \"description\": \"Largest Country in the World\",\r\n"
				+ "        \"correctAnswer\": \"Russia\",\r\n" + "        \"options\": [\r\n"
				+ "            \"India\",\r\n" + "            \"Russia\",\r\n" + "            \"United States\",\r\n"
				+ "            \"China\"\r\n" + "        ]\r\n" + "    },\r\n" + "    {\r\n"
				+ "        \"id\": \"Question2\",\r\n"
				+ "        \"description\": \"Most Populus Country in the World\",\r\n"
				+ "        \"correctAnswer\": \"China\",\r\n" + "        \"options\": [\r\n"
				+ "            \"India\",\r\n" + "            \"Russia\",\r\n" + "            \"United States\",\r\n"
				+ "            \"China\"\r\n" + "        ]\r\n" + "    },\r\n" + "    {\r\n"
				+ "        \"id\": \"Question3\",\r\n" + "        \"description\": \"Highest GDP in the World\",\r\n"
				+ "        \"correctAnswer\": \"United States\",\r\n" + "        \"options\": [\r\n"
				+ "            \"India\",\r\n" + "            \"Russia\",\r\n" + "            \"United States\",\r\n"
				+ "            \"China\"\r\n" + "        ]\r\n" + "    },\r\n" + "    {\r\n"
				+ "        \"id\": \"Question4\",\r\n"
				+ "        \"description\": \"Second largest english speaking country\",\r\n"
				+ "        \"correctAnswer\": \"India\",\r\n" + "        \"options\": [\r\n"
				+ "            \"India\",\r\n" + "            \"Russia\",\r\n" + "            \"United States\",\r\n"
				+ "            \"China\"\r\n" + "        ]\r\n" + "    }  ]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void retrieveSurveyQuestions() throws Exception {

		String url = CreateURL("/surveys/Survey1/questions");

		ResponseEntity<List<Question>> response = restTemplate.exchange(url, HttpMethod.GET,
				new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
				new ParameterizedTypeReference<List<Question>>() {
				});

		Question sampleQuestion = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		assertTrue(response.getBody().contains(sampleQuestion));
	}

}
