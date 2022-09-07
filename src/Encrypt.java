import java.util.Scanner;

public class Encrypt {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Encode or decode?");
		String mode = scanner.nextLine();
		System.out.print("Enter text:");
		String text = scanner.nextLine();
		System.out.print("Enter Key:");
		String key = scanner.nextLine();
		scanner.close();
		if (mode.contains("enc")) {
			System.out.println(VigenereEncode(text, key));
		}else if(mode.contains("dec")) {
			System.out.println(VigenereDecode(text,key));
		}
	}

	public static String VigenereEncode(String text, String key) {
		
		int[] textAsInts = StringToIntArray(removePunct(text.toLowerCase())); 	// Convert messy string to clean int[] (a=0)
		int[] keyAsInt = StringToIntArray(key.toLowerCase());					// Do the same for the key
		int keyLength = key.length();
		char[] encryptChars = new char[textAsInts.length];						// This is where we will store the encrypted
		for (int i = 0; i < textAsInts.length; i++) {
			encryptChars[i] = (char) ('a' + ((textAsInts[i] + keyAsInt[i % keyLength]) % 26));
		}

		return String.valueOf(encryptChars);
	}

	public static String removePunct(String text) {
		return text.replaceAll("[^a-z]", "");
	}

	public static int[] StringToIntArray(String cleanTextString) {
		char[] cleanText = cleanTextString.toCharArray();
		int[] textAsInts = new int[cleanText.length];
		for (int i = 0; i < cleanText.length; i++) {
			textAsInts[i] = cleanText[i] - 'a';
		}
		return textAsInts;
	}
	public static String VigenereDecode(String Ciphertext, String key) {
		
		int[] textAsInts = StringToIntArray(Ciphertext);
		int[] keyAsInt = StringToIntArray(key.toLowerCase());	
		int keyLength = key.length();
		char[] decryptChars = new char[textAsInts.length];		
		for (int i = 0; i < textAsInts.length; i++) {
			decryptChars[i]= (char) ('a' + (textAsInts[i] + (26-keyAsInt[i % keyLength])) % 26);
		}
		return String.valueOf(decryptChars);
	}

}
