package utils;

public class StringHandler {

	public static String removeLastChar(String string) {
		StringBuffer sb = new StringBuffer(string);
		if (string.substring(string.length() - 1).equals("/")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString().trim();
	}
}
