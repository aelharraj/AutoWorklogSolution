package jira.worklog;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestInterceptor;

public class Auto_worlog_imp {
	public Auto_worlog_imp() {
	}

	static RestTemplate restTemplate;

	static JsonHandler jsonHandler = new JsonHandler();
	static String username = "aelharra";
	static String password = "00@Abdel00@elh";
	static String epoTasks[] = { "PNEWF-1836", "PNEWF-1837", "PNEWF-0000", "1903" };

	
	public static void main(String[] args, String userName, char[] passwordChars, String jiraUrlOrg) {
		String passwordText = new String(passwordChars);
		System.out.println("jiraUrlOrg: "+jiraUrlOrg.lastIndexOf(jiraUrlOrg));
		String fullUrl=JiraJql.baseURI +String.format(JiraJql.JQL_CURRENT_USER,userName, "2022-03-01");
		// fields
		String jiraTaskNumberField = "customfield_12049";
		String totalTasks = "total";

		int total = ApiHandler
				.getTask(fullUrl, userName,
						passwordText)
				.getBody().get(totalTasks).asInt();
		System.out.println("Total: " + total);
		System.out.println("Body\n: " + ApiHandler
				.getTask(String.format(JiraJql.baseURI + JiraJql.JQL_WORKLOG_BY_USER_AND_DATE, "2022-03-01"), userName,
						passwordText)
				.getBody());
		int i;
		int tableMax = 0;
		String jiraEpoTaskNumber = "";
		while (tableMax < 4) {
			i = 0;

			while (i < total) {
				jiraEpoTaskNumber = ApiHandler
						.getTask(String.format(JiraJql.baseURI + JiraJql.JQL_WORKLOG_BY_USER_AND_DATE, "2022-03-01"),userName, passwordText).getBody().get("issues").get(i).get("fields").get(jiraTaskNumberField).asText();
				String kosinJiraTaskNumber = ApiHandler
						.getTask(String.format(JiraJql.baseURI + JiraJql.JQL_WORKLOG_BY_USER_AND_DATE, "2022-03-01"),userName, passwordText).getBody().get("issues").get(i).get("key").asText();

				if (epoTasks[tableMax].equalsIgnoreCase(jiraEpoTaskNumber)) {

					System.out.println("************************ TRUE ===> N° " + tableMax);

					i = total;
				}

				i++;
			}
			tableMax++;
		}

	}

	private static void postTask(String baseURI, String data) {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add((ClientHttpRequestInterceptor) ApiHandler.createHeaders(username, password));
		restTemplate.setInterceptors(interceptors);
		URI endpoint = UriComponentsBuilder.fromHttpUrl(baseURI).build().toUri();
		JsonNode body = jsonHandler.createPostBody(data);
		ResponseEntity<JsonNode> response = restTemplate.exchange(endpoint, HttpMethod.POST,
				new HttpEntity<T>(ApiHandler.createHeaders(username, password)),
				new ParameterizedTypeReference<JsonNode>() {
				});
	}

}
