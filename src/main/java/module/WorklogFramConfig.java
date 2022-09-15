package module;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Desktop;

import javax.swing.SwingConstants;

import jira.worklog.ApiHandler;
import jira.worklog.Auto_worlog_imp;
import jira.worklog.JiraWorklogRunner;
import utils.Tools;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

/**
 * TO BE UPDATED TO TAKE INTO ACCOUNT OTHER KIND OF JIRAS, like for example if
 * E.po then it should take only the task from excel
 * 
 * @author aelharra
 *
 */
public class WorklogFramConfig {

	private JFrame frmAutojiraWorklog;
	private JTextField txtUserName;
	private JPasswordField passwordField;
	private JTextField txtJiraUrlOrg;
	boolean isExternalTaskTickBoxSelected=true;
	JButton btnStartWorklog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorklogFramConfig window = new WorklogFramConfig();
					window.frmAutojiraWorklog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WorklogFramConfig() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutojiraWorklog = new JFrame();
		try {
			frmAutojiraWorklog.setIconImage((ImageIO.read(new File("images\\frmIcon.png"))));
			System.out.println("The Tool is running");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frmAutojiraWorklog.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frmAutojiraWorklog.getContentPane().setBackground(SystemColor.window);
		frmAutojiraWorklog.setTitle("Auto-Jira worklog");
		frmAutojiraWorklog.setBackground(SystemColor.info);
		frmAutojiraWorklog.setResizable(false);
		frmAutojiraWorklog.setBounds(100, 100, 1018, 367);
		frmAutojiraWorklog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAutojiraWorklog.getContentPane().setLayout(null);

		JLabel lblAutoKosinworklogVersion = new JLabel("Auto Jira-WorkLog version 1.0.0 2022.04.06");
		lblAutoKosinworklogVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutoKosinworklogVersion.setForeground(SystemColor.textHighlightText);
		lblAutoKosinworklogVersion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblAutoKosinworklogVersion.setBorder(new LineBorder(new Color(154, 205, 50), 2));
		lblAutoKosinworklogVersion.setBackground(SystemColor.control);
		lblAutoKosinworklogVersion.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblAutoKosinworklogVersion.setBounds(12, 13, 974, 31);
		frmAutojiraWorklog.getContentPane().add(lblAutoKosinworklogVersion);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(4, 2, 0, 0, (Color) new Color(154, 205, 50)));
		panel_1.setBackground(SystemColor.scrollbar);
		panel_1.setBounds(12, 13, 974, 173);
		frmAutojiraWorklog.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Dialog", Font.BOLD, 16));
		txtUserName.setBounds(445, 51, 233, 30);
		panel_1.add(txtUserName);
		txtUserName.setHorizontalAlignment(SwingConstants.LEFT);
		txtUserName.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
		txtUserName.setColumns(10);

		propertyValuesToTextField(txtUserName, "USER_NAME");

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUserName.setBounds(321, 51, 91, 30);
		panel_1.add(lblUserName);
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setBounds(321, 92, 91, 30);
		panel_1.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.BOLD, 16));
		passwordField.setBounds(445, 94, 233, 30);
		panel_1.add(passwordField);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));
		propertyValuesToTextField(passwordField, "PASSWORD");

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBorder(new MatteBorder(4, 4, 0, 0, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(0, 0, 974, 30);
		panel_1.add(panel_3);

		JLabel lblJiraUrlOrg = new JLabel("Jira URL ORG:");
		lblJiraUrlOrg.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblJiraUrlOrg.setBounds(321, 140, 148, 16);
		panel_1.add(lblJiraUrlOrg);

		txtJiraUrlOrg = new JTextField();
		txtJiraUrlOrg.setBorder(new MatteBorder(0, 2, 2, 0, (Color) new Color(0, 0, 0)));
		txtJiraUrlOrg.setText("https://umane.everis.com/jiraito");
		txtJiraUrlOrg.setColumns(10);
		txtJiraUrlOrg.setBounds(445, 137, 233, 30);
		panel_1.add(txtJiraUrlOrg);
		propertyValuesToTextField(txtJiraUrlOrg, "JIRA_URL_ORG");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_4.setBounds(299, 43, 421, 130);
		panel_1.add(panel_4);

		JPanel panel = new JPanel();
		panel.setFocusCycleRoot(true);
		panel.setBorder(new MatteBorder(0, 0, 3, 2, (Color) new Color(154, 205, 50)));
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(12, 124, 974, 186);
		frmAutojiraWorklog.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnExcelOpen = new JButton("");
		btnExcelOpen.setForeground(UIManager.getColor("Button.highlight"));
		btnExcelOpen.setBackground(UIManager.getColor("Button.shadow"));
		btnExcelOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnExcelOpen.setEnabled(false);
					Desktop.getDesktop().open(new File("Tareas_worklog.xlsx"));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(btnExcelOpen, JOptionPane.ERROR_MESSAGE,
							"Error while opening excel file " + e.getMessage(), 0);

				}

			}
		});
		btnExcelOpen.setBounds(871, 61, 91, 90);
		panel.add(btnExcelOpen);
		btnExcelOpen.setBorder(new MatteBorder(3, 3, 0, 0, (Color) new Color(0, 0, 0)));
		btnExcelOpen.setIcon(new ImageIcon("images/ExcelIcon.png"));
		btnExcelOpen.setRolloverIcon(new ImageIcon("images/ExcelIconRollover.png"));

		JLabel lblNote = new JLabel(
				"<html><h4 div ><img alt='' height='15' src='file:images\\info.png' width='20'/>&nbsp;&nbsp;Note: Your password & username will be automatically saved under the path: "
						+ System.getProperty("user.home") + "\\auto_jira_worklog\\config.properties"
						+ "</div></h4>  </html> ");
		lblNote.setBounds(0, 155, 974, 30);
		panel.add(lblNote);
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		lblNote.setForeground(new Color(240, 230, 140));
		lblNote.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNote.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNote.setBorder(new LineBorder(new Color(154, 205, 50), 2));
		lblNote.setBackground(Color.BLUE);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 155, 974, 30);
		panel.add(panel_2);
								
										btnStartWorklog = new JButton("Start Auto-Worklog");
										btnStartWorklog.setBackground(new Color(255, 255, 204));
										btnStartWorklog.setForeground(new Color(34, 139, 34));
										btnStartWorklog.setVisible(false);
										btnStartWorklog.setBounds(525, 79, 181, 36);
										panel.add(btnStartWorklog);
										btnStartWorklog.setFont(new Font("Century Gothic", Font.BOLD, 16));
										btnStartWorklog.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												String[] arguments = new String[] { "this is a test" };
												new Auto_worlog_imp();

												if (!txtUserName.getText().equals("") & !(passwordField.getPassword().length == 0)) {
													// f.dispose();
													try {
														updatePropertiesFile();

														JiraWorklogRunner.main(arguments, txtUserName.getText(), passwordField.getPassword(),
																txtJiraUrlOrg.getText(),isExternalTaskTickBoxSelected);
														JOptionPane.showMessageDialog(btnStartWorklog,"All tasks in the excel file are successfully logged\n Click OK to close the tool");

														System.exit(0);
													} catch (Exception e1) {
														JOptionPane.showMessageDialog(btnStartWorklog, "An error has been occured:\n " + e1.getMessage());
													}
												} else {
													JOptionPane.showMessageDialog(btnStartWorklog, "Password and/or User name cannot be empty");
													return;
												}
												System.exit(0);
											}
										});
										btnStartWorklog.setBorder(new MatteBorder(0, 0, 3, 3, (Color) new Color(0, 0, 0)));
										
										JPanel panel_5 = new JPanel();
										panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
										panel_5.setBounds(300, 69, 421, 57);
										panel.add(panel_5);
										panel_5.setLayout(null);
										
												JButton btnCheckAuthorisation = new JButton("Check Authorisation");
												btnCheckAuthorisation.setBackground(SystemColor.controlHighlight);
												btnCheckAuthorisation.setBounds(228, 13, 181, 36);
												panel_5.add(btnCheckAuthorisation);
												btnCheckAuthorisation.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														String[] arguments = new String[] { "this is a test" };
														int statusCode=0;
													//	updatePropertiesFile();

														try {

															statusCode = ApiHandler.apiResponseStatus(txtJiraUrlOrg.getText(), txtUserName.getText(),
																	new String(passwordField.getPassword()));
															if (statusCode == 200 || statusCode == 204)
															{
																JOptionPane.showMessageDialog(btnCheckAuthorisation,  "Successful connection");
																btnCheckAuthorisation.setVisible(false);
																btnStartWorklog.setVisible(true);

															}
														} catch (Exception e) {
															JOptionPane.showMessageDialog(btnCheckAuthorisation,  "Connection failed");
															return;
														}

													}
												});
												btnCheckAuthorisation.setFont(new Font("Dialog", Font.PLAIN, 16));
												btnCheckAuthorisation.setBorder(new MatteBorder(0, 0, 3, 3, (Color) new Color(0, 0, 0)));
												
												JCheckBox chckbxExternalJiraTasks = new JCheckBox("Tasks from external Jira org");
												chckbxExternalJiraTasks.setBounds(12, 13, 185, 36);
												panel_5.add(chckbxExternalJiraTasks);
												chckbxExternalJiraTasks.setBackground(SystemColor.control);
												chckbxExternalJiraTasks.setSelected(true);
												chckbxExternalJiraTasks.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														if(chckbxExternalJiraTasks.isSelected())
														{
															isExternalTaskTickBoxSelected=true;
														}
														else{ isExternalTaskTickBoxSelected=false;}
													}
												});
												chckbxExternalJiraTasks.setBorder(new MatteBorder(0, 0, 3, 3, (Color) SystemColor.desktop));

	}

	public void propertyValuesToTextField(JTextField comp, String PropertyTag) {
		try {
			File dir = new File(System.getProperty("user.home") + "\\auto_jira_worklog");
			if (!dir.exists()) {
				if (dir.mkdirs()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
			File fileName = new File(dir + "\\" + "config.properties");
			try {
				if (!fileName.exists())
					fileName.createNewFile();
				//System.out.println(dir + "\\" + fileName.getName()+" exists/created");
			} catch (IOException ioe) {
				System.out.println("Error while Creating File in Java" + ioe);
			}
			// load a properties file from class path, inside static metod
			String propertyValue = Tools.readPropertyUserHome(dir + "\\" + "config.properties", PropertyTag);
			comp.setText(propertyValue);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage() + " jiraUrlOrgTextFieldGetter");
		}

	}
	
	public void updatePropertiesFile()
	{
		Tools.updatePropertyFile(
				System.getProperty("user.home") + "/auto_jira_worklog/config.properties", "USER_NAME",
				txtUserName.getText());
		Tools.updatePropertyFile(
				System.getProperty("user.home") + "/auto_jira_worklog/config.properties", "PASSWORD",
				new String(passwordField.getPassword()));
		Tools.updatePropertyFile(
				System.getProperty("user.home") + "/auto_jira_worklog/config.properties",
				"JIRA_URL_ORG", new String(txtJiraUrlOrg.getText()));

	}
}
