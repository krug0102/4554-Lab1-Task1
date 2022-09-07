import java.util.Scanner;

public class Decryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		System.out.print("Enter text:");
		String text = scanner.nextLine();
        System.out.print("Enter length of key:");
		int keyLength = scanner.nextInt();
		freqs(everyNth(text, keyLength));
		scanner.close();
    }

    public static void freqs(String cipherText) {
        int[] letters = new int[26];
        for(char letter : cipherText.toCharArray()) {
            letters[letter - 'a']++;

        }
        for(int i = 0; i < 26; i++) {
            float percentage = (float)letters[i] / cipherText.length();
            System.out.println(percentage * 100);
        }
    }

    public static String everyNth(String text, int n) {
        String result = "";
        for(int i = 1; i < text.length(); i+=n) {
            result = result + text.charAt(i);
        }
        return result;
    }

}