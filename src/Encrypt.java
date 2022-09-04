import java.util.Scanner;

public class Encrypt {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text:");
        String text = scanner.nextLine();
        System.out.print("Enter Key:");
        String key = scanner.nextLine();
        System.out.println(VigenereEncrypt(text.toLowerCase(), key));
        scanner.close();
    }


public static String VigenereEncrypt(String text, String key) {
    char[] cleanText = removePunct(text).toCharArray();
    char[] keyChars = key.toCharArray();
    for (int i = 0; i < cleanText.length; i++) {
        cleanText[i] = (char)((((cleanText[i] - 'a') + (keyChars[i % keyChars.length] - 'a')) % 26) + 'a');
    }
    return String.copyValueOf(cleanText);
}

public static String VigenereDecrypt(String text, String key) {
    char[] cleanText = removePunct(text).toCharArray();
    char[] keyChars = key.toCharArray();
    for (int i = 0; i < cleanText.length; i++) {
        int a = (((cleanText[i] - 'a') - (keyChars[i % keyChars.length] - 'a')) % 26);
        if (a < 0) {
            cleanText[i] = (char)(a + 26 + 'a');
        }
        else {
            cleanText[i] = (char)(a + 'a');
        }
    }
    return String.copyValueOf(cleanText);
}

public static String removePunct(String text) {
    return text.replaceAll("[^a-z]", "");
}
}
