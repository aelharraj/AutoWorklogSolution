package jira.worklog;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.fusesource.jansi.AnsiConsole;

import utils.Colors;
import utils.ReadExcelData_JiraIncurridos;
import utils.Tools;

/**
 * Hello world!
 *
 */
public class App 
{
		static String jiraUrlOrg="https://umane.everis.com/jiraito/";

    public static void main(String[] args)
    {
    	String username = "aelharra";
    	String password = "00@Abdel00@elh";
    	 String jsonBodyData="{ 'comment': '%s', 'started': '%s','timeSpent': '%s'}";
        //System.out.println(String.format(jsonBodyData,"Testing","2022-03-31","0.5h"));
        //System.out.println(JsonHandler.createPostBody(String.format(jsonBodyData,"Testing","2022-03-31","0.5h")));
        
		  //  AnsiConsole.systemInstall();

    	 
//    	 String kosinJiraTaskNumber = ApiHandler
//					.getTask("https://umane.everis.com/jiraito/rest/api/latest/search?jql= text ~ 'PNEWF-1836'",username, password).getBody().get("issues").get(0).get("key").asText();
    	 
    	// Colors.loading();
    	 
    // System.out.println("kosinJiraTaskNumber: "+ApiHandler.newJiraOrgTaskNumber("https://umane.everis.com/jiraito","PNEWF-1836", username, password ));
     
		//System.out.println(StringHandler.removeLastChar(jiraUrlOrg));
    	 
    	 
       //DONE ---- ApiHandler.postWorkLog(String.format(JiraJql.URL_POST_WORKLOG,"EPOAGILE-370"), String.format(jsonBodyData,"Testing","2019-03-06"+ "T05:" + Tools.RandomNumber() + Tools.RandomNumber() + ":31.165+0200","0.5h"), username, password);

    	 System.out.println(Tools.filePath(new File("timeSheetReport\\TimeSheetReport_2022_04_10-22_51_11.html")));
    	// Tools.openFile("timeSheetReport\\TimeSheetReport_2022_04_10-22_51_11.html");
    	 try {
 			Tools.openWebpage(new URL("file:"+Tools.filePath(new File("timeSheetReport\\TimeSheetReport_2022_04_10-22_51_11.html"))));
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}    
    }
    
    

}
//2022-04-05T02:35:31.165+0200