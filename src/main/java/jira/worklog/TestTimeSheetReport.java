package jira.worklog;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.generateReport;

public class TestTimeSheetReport {
	static DateFormat dateFormatLog = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static DateFormat dateFormatFileName = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
	static Date date = new Date();

	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		URL uri = new URL(
				"https://umane.everis.com/jiraito/rest/api/TimesheetReport!excelView.jspa?htmlExport=true&page=2&reportKey=jira-timesheet-plugin:report&targetUser=aelharra&weekends=true&showDetails=true&sum=day&startDate=2022-03-15&endDate=2022-03-31&reportingDay=2");
		String htmlFile = ApiHandler.getStringResponse(uri.toURI().toString(), "aelharra", "00@Abdel00@elh").getBody();
		//generateReport.writeHtmlFile(htmlFile);
	}

	

}