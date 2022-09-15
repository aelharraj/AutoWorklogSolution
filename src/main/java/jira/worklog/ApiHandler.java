package jira.worklog;

import java.net.URI;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.MediaType;

public class ApiHandler {

	// send pass in the header
	static HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
				setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
			}
		};
	}

	public static ResponseEntity<JsonNode> getTask(String baseURI, String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JsonNode> response = null;
		try {

			URI endpoint = UriComponentsBuilder.fromHttpUrl(baseURI).build().toUri();

			// return restTemplate.getForEntity(endpoint,new
			// HttpEntity<T>(createHeaders(username, password), String.class);
			response = restTemplate.exchange(endpoint, HttpMethod.GET,
					new HttpEntity<T>(createHeaders(username, password)), new ParameterizedTypeReference<JsonNode>() {
					});
		} catch (Exception e) {
			// System.out.println("error: "+e.getMessage());
		}
		System.out.println("continue doing something for GET TASK");
		return response;
	}

	public static void postWorkLog(String baseURI, String jsonBodyData, String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		MediaType.parse("application/json");

		// List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		// interceptors.add((ClientHttpRequestInterceptor)
		// ApiHandler.createHeaders(username, password));
		// restTemplate.setInterceptors(interceptors);
		try {

			URI endpoint = UriComponentsBuilder.fromHttpUrl(baseURI).build().toUri();
			JsonNode body = JsonHandler.createPostBody(jsonBodyData);

			HttpEntity<String> request = new HttpEntity<String>(body.toString(),
					ApiHandler.createHeaders(username, password));

			restTemplate.postForObject(endpoint, request, String.class);

		} catch (Exception e) {
			// System.out.println("POST ERROR: "+e.getMessage());
		}
		System.out.println("continue doing something for post");
	}

	public static String newJiraOrgTaskNumber(String jiraUrlOrg, String srchJiraTaskNumber, String username,
			String password) {
		String response = "";
		try {
			response = ApiHandler
					.getTask(jiraUrlOrg + "/rest/api/latest/search?jql= 'ID client request' ~" + srchJiraTaskNumber
							+ " OR summary ~ '" + srchJiraTaskNumber + "'", username, password)
					.getBody().get("issues").get(0).get("key").asText();
		} catch (Exception e) {
			 response = "THIS TASK IS NOT FOUND";
		}
		return response;
	}

	public static int getResponseStatus(String baseURI, String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JsonNode> response = null;
		int status = 0;
		try {

			URI endpoint = UriComponentsBuilder.fromHttpUrl(baseURI).build().toUri();

			// return restTemplate.getForEntity(endpoint,new
			// HttpEntity<T>(createHeaders(username, password), String.class);
			response = restTemplate.exchange(endpoint, HttpMethod.GET,
					new HttpEntity<T>(createHeaders(username, password)), new ParameterizedTypeReference<JsonNode>() {
					});
			status = response.getStatusCodeValue();
		} catch (RestClientException e) {
			status = response.getStatusCodeValue();
			System.out.println("error: " + e);
		}
		return status;
	}

	public static int apiResponseStatus(String jiraUrlOrg, String username, String password) {
		return ApiHandler.getResponseStatus(jiraUrlOrg + "/rest/api/2/project", username, password);
	}

	/**
	 * Used for retrieving the report with html format
	 * 
	 * @param baseURI
	 * @param username
	 * @param password
	 * @return
	 */
	public static ResponseEntity<String> getStringResponse(String baseURI, String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {

			URI endpoint = UriComponentsBuilder.fromHttpUrl(baseURI).build().toUri();

			// return restTemplate.getForEntity(endpoint,new
			// HttpEntity<T>(createHeaders(username, password), String.class);
			response = restTemplate.exchange(endpoint, HttpMethod.GET,
					new HttpEntity<T>(createHeadersAtomXML(username, password)),
					new ParameterizedTypeReference<String>() {
					});
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
		return response;
	}

	/**
	 * Used to send pass & username with ContentType set to ATOM XML to read
	 * excel format
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	static HttpHeaders createHeadersAtomXML(String username, String password) {
		return new HttpHeaders() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
				setContentType(org.springframework.http.MediaType.APPLICATION_ATOM_XML);
			}
		};
	}

}
