package jira.worklog;

import java.util.ArrayList;

import org.fusesource.jansi.AnsiConsole;

import utils.Colors;
import utils.ReadExcelData_JiraIncurridos;
import utils.StringHandler;
import utils.Tools;
import utils.generateReport;

public class JiraWorklogRunner {
	static String hours;
	static String jiraNumber;
	static String date;
	static String description;
	static boolean traceLog;

	public static void main(String[] args, String userName, char[] passwordChars, String jiraUrlOrg, Boolean isExternalTaskTickBoxSelected) {

		String passwordText = new String(passwordChars);
		String orgUrl=StringHandler.removeLastChar(jiraUrlOrg);
		String kosinJiraTaskNumber="DEFAULT INTERNAL TASK";
		String startDate="";
		String endDate="";
		
		ArrayList<ArrayList<String>> TareasData = new ArrayList<ArrayList<String>>();
		ArrayList<String> textLogger = new ArrayList<>();
		ArrayList<String> textLoggerErrors = new ArrayList<>();
		Colors.printVersion();
		Tools.startTime();
	//	Colors.loading();
 
		try {

			TareasData = ReadExcelData_JiraIncurridos.listData();

			for (int indexGlobalList = 0; indexGlobalList < TareasData.size(); indexGlobalList++) {
				jiraNumber = TareasData.get(indexGlobalList).get(0);
				hours = TareasData.get(indexGlobalList).get(1).replace(",", ".");
				date = TareasData.get(indexGlobalList).get(2).replace(".", "-").replace("/", "-");
				description = TareasData.get(indexGlobalList).get(3);
				String jiraEpoNumber = jiraNumber.trim().toString();
				startDate=TareasData.get(0).get(2);
				endDate=TareasData.get(TareasData.size()-1).get(2);
				if(isExternalTaskTickBoxSelected==false)
				{
					System.out.println("Logging INTERNAL tasks");
					ApiHandler.postWorkLog(String.format(orgUrl+JiraJql.URL_POST_WORKLOG,jiraEpoNumber),
					String.format(JsonHandler.jsonBodyData,description,date+ "T05:" +
					Tools.RandomNumber() + Tools.RandomNumber() +
					 ":31.165+0200",hours+"h"), userName, passwordText);
				}
				else
				{
					System.out.println("Logging EXTERNAL tasks");
					try {
						kosinJiraTaskNumber= ApiHandler.newJiraOrgTaskNumber(orgUrl, jiraNumber, userName, new String(passwordChars));
					} catch (Exception e) {
					}
					if(kosinJiraTaskNumber.equals("THIS TASK IS NOT FOUND")|| kosinJiraTaskNumber.equals("")){System.out.println("The Task not found");kosinJiraTaskNumber="This task not found!";}
					else
					ApiHandler.postWorkLog(String.format(orgUrl+JiraJql.URL_POST_WORKLOG,kosinJiraTaskNumber),
					String.format(JsonHandler.jsonBodyData,description,date+ "T05:" +
					Tools.RandomNumber() + Tools.RandomNumber() +
					 ":31.165+0200",hours+"h"), userName, passwordText);
				}
				screenTrace(jiraEpoNumber, hours, date, description, kosinJiraTaskNumber );
				traceLog=true;
				textLogger.add(jiraEpoNumber);
				textLogger.add(hours);
				textLogger.add(date);
				textLogger.add(description);
				textLogger.add(kosinJiraTaskNumber);

			}
		} catch (Exception e) {
			traceLog=false;
			textLoggerErrors.add(e.getMessage());
			}
		System.out.println("\n ======> GENERATING JIRA TIME SHEET REPORT.. <=======");

		generateReport.writeFile(textLogger, traceLog, textLoggerErrors, "Tasks");
		String htmlContent=ApiHandler.getStringResponse(orgUrl+String.format(JiraJql.timeSheetURL,userName,startDate,endDate), userName, passwordText).getBody();
		generateReport.writeHtmlFile(htmlContent,startDate,endDate,orgUrl+String.format(JiraJql.timeSheetURL,userName,startDate,endDate));
		


	}
	
	
	
	public static void screenTrace(String jiraEpoNumber, String hours, String date, String description, String kosinJiraTaskNumber )
	{
		 
		AnsiConsole.out.println("--------- WORK LOG ---------\n");
	
		Colors.printText("Task: "+jiraEpoNumber);
		Colors.printText("Worked Hours: "+hours);
		Colors.printText("date: "+date);
		Colors.printText("Comment: "+description);
		Colors.printText("Log work done in: "+kosinJiraTaskNumber);
	}
}
