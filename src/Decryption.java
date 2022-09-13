import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Decryption {

    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text:");
        String text = scanner.nextLine();
        tryKeylenths(text);
        scanner.close();
    }

    public static void freqs(String cipherText) {
        int[] letters = new int[26];
        float[] letterFrequency = new float[26];
        for (char letter : cipherText.toCharArray()) {
            letters[letter - 'a']++;

        }
        for (int i = 0; i < 26; i++) {
            letterFrequency[i] = (float) letters[i] / cipherText.length();
        }
        tryandfindAEIONT(letterFrequency);
    }
    // Starting at i, get every nth letter from a string. e.g. i=4 n=8 gets the 4th 12th 20th, 28th.. letter
    public static String everyNth(String text, int n, int i) { 
        String result = "";
        for (; i < text.length(); i += n) {
            result = result + text.charAt(i);
        }
        return result;
    }
    // The first of a few iterations on finding delta for a list of ratios. AET are the three most common letters in english use
    public static void tryandfindAET(float[] ratios) {
        int LetterPossibilities = 0;
        ArrayList<Float> k = new ArrayList<Float>();
        for (int i = 0; i < ratios.length; i++) {
            if ((ratios[i] + ratios[(i + 4) % 26] + ratios[(i + 19) % 26]) > 0.18) {
                LetterPossibilities++;
                System.out.print(alphabet[i] + ", ");
            }
            k.add(i, ratios[i] + ratios[(i + 4) % 26] + ratios[(i + 19) % 26]);
        }
        System.out.println(alphabet[k.indexOf(Collections.max(k))] + " is most likely. " + LetterPossibilities);
    }
    // Given text encoded by a vignere cipher, try decrypting it for key length of [5,9)
    private static void tryKeylenths(String text) {
        for (int i = 5; i < 9; i++) {
            System.out.println("Key length: " + i);
            tryLength(text, i);
        }
    }
    // Given a text encoded by a vignere cipher, and a keylength, for ever possible starting index take every nth (n=keylength) letter,
    // and do a frequency analysis on it.
    private static void tryLength(String text, int keyLength) {
        for (int i = 0; i < keyLength; i++)
            freqs(everyNth(text, keyLength, i));
    }
    // Oddly, the second of the iterations. was worried about getting Gadsby-d
    public static void tryandfindAT(float[] ratios) {
        int LetterPossibilities = 0;
        ArrayList<Float> k = new ArrayList<Float>();
        for (int i = 0; i < ratios.length; i++) {
            if (ratios[i] + ratios[(i + 19) % 26] > 0.135) {
                LetterPossibilities++;
                System.out.print(alphabet[i] + ", ");
            }
            k.add(i, ratios[i] + ratios[(i + 19) % 26]);
        }
        System.out.println(alphabet[k.indexOf(Collections.max(k))] + " is most likely. " + LetterPossibilities);
    }
    // After a lot of tinkering on the previous 2 iterations (damn you Nik Gilbertson) I decided to just go to the top 6 most common
    // letters in English AEIONT. 0.46 is a really good value to check against imo. Looks for E, but is roubust against Gadsby.
    // Also should definitly generalize the whole ratios[i] + ratios[(i + n) % 26] + ratios[(i + m) % 26] + ratios[(i + l) % 26] bit
    public static void tryandfindAEIONT(float[] ratios) {
        int LetterPossibilities = 0;
        ArrayList<Float> k = new ArrayList<Float>();
        for (int i = 0; i < ratios.length; i++) {
            if (ratios[i] + ratios[(i + 4) % 26] + ratios[(i + 8) % 26] + ratios[(i + 13) % 26] + ratios[(i + 14) % 26] + ratios[(i + 19) % 26] > 0.46) {
                LetterPossibilities++;
                System.out.print(alphabet[i] + ", ");
            }
            k.add(i, ratios[i] + ratios[(i + 4) % 26] + ratios[(i + 8) % 26] + ratios[(i + 13) % 26] + ratios[(i + 14) % 26] + ratios[(i + 19) % 26]);
        }
        System.out.println(alphabet[k.indexOf(Collections.max(k))] + " is most likely. " + LetterPossibilities);
    }
}