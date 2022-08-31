import java.util.Scanner;

public class Encrypt {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text:");
        String text = scanner.nextLine();
        System.out.print("Enter Key:");
        String key = scanner.nextLine();
        System.out.println(VigenereEncode(text.toLowerCase(), key));

    }


public static char[] VigenereEncode(String text, String key) {
    char[] cleanText = removePunct(text).toCharArray();
    int[] textAsInts = StringToIntArray(cleanText);
    int[] keyAsInt = StringToIntArray(key.toCharArray());
    int keyLength = key.length();
    int[] encryptChars = new int[cleanText.length];
    for (int i = 0; i<textAsInts.length; i++){
        encryptChars[i] = 'a'+(cleanText[i]+keyAsInt[i%keyLength])%26;
    }
    
    return char[] (encryptChars);
}

public static String removePunct(String text) {
    return text.replaceAll("[^a-z]", "");
}

public static int[] StringToIntArray (char[] cleanText){
    int[] textAsInts = new int[cleanText.length];
    for(int i = 0;i<cleanText.length;i++){
        textAsInts[i] = cleanText[i] - 'a';
    }
    return textAsInts;
}
}
