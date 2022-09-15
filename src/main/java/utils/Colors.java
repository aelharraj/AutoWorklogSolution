package utils;

import org.fusesource.jansi.AnsiConsole;

public class Colors {
	public static final String ANSI_CLS = "\u001b[2J";
	public static final String ANSI_HOME = "\u001b[H";
	public static final String ANSI_BOLD = "\u001b[1m";
	public static final String ANSI_AT55 = "\u001b[10;10H";
	public static final String ANSI_REVERSEON = "\u001b[7m";
	public static final String ANSI_NORMAL = "\u001b[0m";
	public static final String ANSI_WHITEONBLUE = "\u001b[37;44m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnsiConsole.systemInstall();
		AnsiConsole.out.println(ANSI_CLS);
		AnsiConsole.out.println(ANSI_AT55 + ANSI_REVERSEON + "Hello world" + ANSI_NORMAL);
		AnsiConsole.out.println(ANSI_HOME + ANSI_WHITEONBLUE + "Hello world" + ANSI_NORMAL);
		AnsiConsole.out.print(ANSI_BOLD + "Press a key..." + ANSI_NORMAL);
		try {
			System.in.read();
		} catch (Exception e) {
		}
		AnsiConsole.out.println(ANSI_CLS);
		AnsiConsole.systemInstall();
		System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
		System.out.println(ANSI_BLUE_BACKGROUND + "This the first round..." + ANSI_WHITE);
		System.out.println(ANSI_WHITE_BACKGROUND + "\t\tpart 1 done ☺" + ANSI_GREEN + ANSI_BOLD);
		System.out.println(ANSI_WHITE_BACKGROUND + "-------------part 2 done------------" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "-------------part 2 done------------" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "-------------part 2 done------------" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "-------------part 2 done------------" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "-------------part 2 done------------" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "-------------part 2 done------------" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "part 3 done ☺" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "part 4 done ☺" + ANSI_GREEN);
		System.out.println(ANSI_WHITE_BACKGROUND + "part 5 done ☺" + ANSI_GREEN);
		System.out.println(ANSI_RED + "This text has red text but a default background!" + ANSI_RESET);
		System.out.println(
				ANSI_GREEN_BACKGROUND + ANSI_RED + "This text has a green background and red text!" + ANSI_RESET);
		// System.out.println(
		// ansi().eraseScreen().fg(RED).a("Hello").fg.(GREEN).a("
		// World").reset() );

	}

	public static void textToUpHomeColor() {
		clearScreen();
		AnsiConsole.out.println(ANSI_BLACK_BACKGROUND + ANSI_HOME);
	}

	public static void loading() {
		AnsiConsole.out.print(ANSI_AT55 + "Starting " + ANSI_BLUE + ANSI_BOLD);
		Tools.wait(500);
		clearScreen();
		AnsiConsole.out.print(ANSI_AT55 + "Starting work" + ANSI_BLUE + ANSI_BOLD);
		Tools.wait(500);
		clearScreen();
		AnsiConsole.out.print(ANSI_AT55 + "Starting work log" + ANSI_BLUE + ANSI_BOLD);
		Tools.wait(500);
		clearScreen();
		AnsiConsole.out.print(ANSI_AT55 + "Starting work log..." + ANSI_BLUE + ANSI_BOLD);
		Tools.wait(1000);
		textToUpHomeColor();
	}

	public static void clearScreen() {
		AnsiConsole.out.println(ANSI_CLS);
	}

	public static void printText(String text) {
		AnsiConsole.systemInstall();
		AnsiConsole.out.println(Colors.ANSI_BLACK_BACKGROUND + text+ Colors.ANSI_CYAN + Colors.ANSI_BOLD);	}
	public static void printVersion()
	{
		AnsiConsole.systemInstall();
		AnsiConsole.out.println(Colors.ANSI_BLACK_BACKGROUND +"+----------------------------------+"+ Colors.ANSI_RED + Colors.ANSI_BOLD);
		AnsiConsole.out.println(Colors.ANSI_BLACK_BACKGROUND +"| Auto-Jira WorkLog V1 2022-04-06  |"+ Colors.ANSI_RED + Colors.ANSI_BOLD);
		AnsiConsole.out.println(Colors.ANSI_BLACK_BACKGROUND +"+----------------------------------+"+ Colors.ANSI_RED + Colors.ANSI_BOLD);

	}
}
