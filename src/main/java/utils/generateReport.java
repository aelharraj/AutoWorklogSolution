package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class generateReport {
	static DateFormat dateFormatLog = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static DateFormat dateFormatFileName = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
	static Date date = new Date();

	public static void open(String targetFilePath) {
		try {
			System.out.println("\n\n\t\tOpening the generated Report\n\n");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(new File(targetFilePath));

		} catch (IOException e) {

			System.err.println(e.getMessage());
		}
	}

	// Create File
	public static void writeFile(ArrayList<String> textLogger, boolean traceType, ArrayList<String> textLoggerErrors,
			String taskType) {// String
		// jiraNumber,String
		System.out.println("\n ======> WORK LOG REPORT <=======");
		try {
			int k = 1;
			String path = "LogFiles_External\\External_logFile_AutoIncurrido";
			if (taskType == "Tasks") {
				path = "WorkLog_logs\\WorkLog_tasks";
			} else if (taskType == "Absences") {
				path = "LogFiles_Absences\\Absences_logFile_AutoIncurrido";
			}
			PrintStream writer = new PrintStream(
					new FileOutputStream(path + dateFormatFileName.format(date) + ".txt", true));
			writer.append("\r\n --------------------------------- Logged on: " + dateFormatLog.format(date)
					+ "  --------------------------------- \r\n\n");
			if (traceType == true) {
				if (taskType == "Tasks") {
					for (int i = 0; i < textLogger.size(); i += 5) {
						writer.append("\r\n           -Task N° " + k + "-\r\n");
						writer.append("\r\n Jira Number: " + textLogger.get(i));
						writer.append("\r\n Worked Hours: " + textLogger.get(i + 1)+ "h");
						writer.append("\r\n Date :" + textLogger.get(i + 2));
						writer.append("\r\n Description: " + textLogger.get(i + 3));
						writer.append("\r\n Work log done in: " + textLogger.get(i + 4));
						writer.append("\r\n Status: Request Successfully Finished \r\n");
						k++;
					}
				} else
					for (int i = 0; i < textLogger.size(); i += 5) {
						writer.append("\r\n           -Task N° " + k + "-\r\n");
						writer.append("\r\n Jira Number: " + textLogger.get(i));
						writer.append("\r\n Worked Hours: " + textLogger.get(i + 1));
						writer.append("\r\n Date :" + textLogger.get(i + 2) + "h");
						writer.append("\r\n Description: " + textLogger.get(i + 3));
						writer.append("\r\n Work log done in: " + textLogger.get(i + 4));
						writer.append("\r\n Status: Request Successfully Finished \r\n");
						k++;
					}
			} else if (traceType == false) {
				if (taskType == "Tareas" & textLogger.size() > 0) {
					for (int i = 0; i < textLogger.size(); i += 5) {
						writer.append("\r\n           -Task N° " + k + "-\r\n");
						writer.append("\r\n Jira Number: " + textLogger.get(i));
						writer.append("\r\n Worked Hours: " + textLogger.get(i + 1));
						writer.append("\r\n Date :" + textLogger.get(i + 2) + "h");
						writer.append("\r\n Description: " + textLogger.get(i + 3));
						writer.append("\r\n Work log done in: " + textLogger.get(i + 4));
						writer.append("\r\n Status: Request Successfully Finished \r\n");
						k++;
					}
				} else if (textLogger.size() > 0)
					for (int i = 0; i < textLogger.size(); i += 5) {
						writer.append("\r\n           -Task N° " + k + "-\r\n");
						writer.append("\r\n Jira Number: " + textLogger.get(i));
						writer.append("\r\n Worked Hours: " + textLogger.get(i + 1));
						writer.append("\r\n Date :" + textLogger.get(i + 2) + "h");
						writer.append("\r\n Description: " + textLogger.get(i + 3));
						writer.append("\r\n Work log done in: " + textLogger.get(i + 4));
						writer.append("\r\n Status: Request Successfully Finished \r\n");
						k++;
					}
				writer.append("\r\n ****** Error Message: *******\r\n" + textLoggerErrors);
			}
			writer.append("\r\n\n |--> Time spent in the worklog execution : " + Tools.EndTime() + " <--| \r\n");

			writer.flush();
			writer.close();
		} catch (Exception err) {
			System.out.println("Error saving file, " + err.getMessage());
		}
		System.out.println("\n ======> log file has been generated <=======");

	}

	/**
	 * GENERATE THE HTML REPORT FROM JIRA
	 * 
	 * @param htmlContent
	 */
	public static void writeHtmlFile(String htmlContent, String startDate, String endDate,String timeSheetURL) {
		String htmlMsgTable = "<div style='background:#036400;'><table class='tg'><thead><tr><td class='tg-nf5t'><span style='color:#FFCC67'>Generated from</span><span style='font-weight:bold;font-style:italic;color:#FFCC67'> </span><span style='font-weight:bold;color:#FFCC67'>Auto-JiraWorkLog</span><span style='font-weight:bold;font-style:italic;color:#FFCC67'> </span><span style='color:#FFCC67'>Tool on: "
				+ dateFormatLog.format(date)
				+ "</span><span style='font-weight:bold;font-style:italic;color:#FFCC67'></span><br><span style='font-weight:bold;color:#ECF4FF'>For anything in (</span><span style='font-weight:bold;font-style:italic;color:#F8A102'>Issues</span><span style='font-weight:bold;color:#ECF4FF'>, </span><span style='font-weight:bold;font-style:italic;color:#FCFF2F'>Questions</span><span style='font-weight:bold;color:#ECF4FF'>) contact Abdelatif El Harraj</span></td></tr></thead></table></div>";
		String htmlMsgTableStartEndDate = "<div class='tg-wrap'><table class='tg'><thead><tr><td class='tg-baqh'><span style='font-weight:bold;color:black;background-color:white'>Note: This Worklog report is between:</span><span style='font-weight:bold;font-style:italic;color:black;background-color:white'> </span><span style='font-weight:bold;font-style:italic;color:blue;background-color:white'>%s</span><span style='font-weight:bold;font-style:italic;color:blue;background-color:white'> and </span><span style='font-weight:bold;font-style:italic;color:blue;background-color:white'>%s </span></td></tr></thead></table></div>";
		String htmlHrefOpenTimeSheet="<div class='tg-wrap'><table class='tg'><thead><tr><td class='tg-c3ow'><span style='font-weight:bold;text-decoration:underline'>Export your timeSheet to Excel</span>: <a href='%s' target='_blank' rel='noopener noreferrer'>Jira TimeSheet Excel from "+startDate+" to "+endDate+"</a></td></tr></thead></table></div>";

		String fileName="";
		try {
			String path = "timeSheetReport\\TimeSheetReport_";
			fileName=path + dateFormatFileName.format(date) + ".html";
			PrintStream writer = new PrintStream(
					new FileOutputStream(fileName, true));
			writer.append(
					"\r\n" + htmlMsgTable + "\r\n\n" + String.format(htmlMsgTableStartEndDate, startDate, endDate)+"\r\n\n"+String.format(htmlHrefOpenTimeSheet,timeSheetURL));
			writer.append(htmlContent);

			writer.flush();
			writer.close();
		} catch (Exception err) {
			System.out.println("Error saving file, " + err.getMessage());
		}
		try {
			Tools.openWebpage(new URL("file:"+Tools.filePath(new File(fileName))));
			System.out.println("\n ======> TIME SHEET REPORT GENERATED SUCCESSFULLY FROM AUTO-JIRA WORKLOG TOOL <=======");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
