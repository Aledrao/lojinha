package br.com.asas.lojinha;

public class TestUtil {

	public static String createStringWithLenght(int length) {
		StringBuilder builder = new StringBuilder();
		
		for(int index = 0; index < length; index ++) {
			builder.append("a");
		}
		return builder.toString();
	}
}
