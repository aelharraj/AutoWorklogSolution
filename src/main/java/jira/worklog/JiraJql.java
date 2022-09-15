package jira.worklog;

import java.net.URL;

public class JiraJql {
	static String REST_API="/rest/api";
	static String jiraUrlOrg;
	static String baseURI = jiraUrlOrg+REST_API;
	//1 %s : username , 2 %s: startDate, 3 %s: endDate
	public static String timeSheetURL ="/rest/api/TimesheetReport!excelView.jspa?htmlExport=true&page=2&reportKey=jira-timesheet-plugin:report&targetUser=%s&weekends=true&showDetails=true&sum=day&startDate=%s&endDate=%s&reportingDay=2";

	
	
	static String JQL_CURRENT_USER_WITH_SPECIFIC_TASK =REST_API+ "/latest/search?jql=assignee in (currentUser()) AND text ~ '%s'"; //to be seen later if we can use it instead of having a big loop
	static String JQL_CURRENT_USER =  REST_API+"/latest/search?jql=assignee in (currentUser())";
	static String JQL_WORKLOG_BY_USER_AND_DATE = REST_API+"/latest/search?jql=worklogAuthor = %s AND worklogDate >= %s";// example to be used to retrieve data from jiraEpo
	static String JQL_SEARCH_BY_CLIENT_ID =  REST_API+"/latest/search?jql=assignee in (currentUser())";
																											// date
																																						// 2022-03-01
	
	static String URL_POST_WORKLOG = REST_API+"/2/issue/%s/worklog";
	static String POST_BODY_WORKLOG = "{ 'comment': '%s', 'started': '%s','timeSpent': '%s'}";


}
