
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GuidSampleDemo {
    public static void main(String[] args) {
        char[] chars = new char[61];
        char[] specialChars = {'!', '@', '#', '$', '%', '&', '_', '+', '?'};
        int position = 0;
        for (int i = 0; i < 26; i++) {
            chars[position++] = (char)('A' + i);
        }
        for (int i = 0; i < 26; i++) {
            chars[position++] = (char)('a' + i);
        }
        for (int i = 0; i < specialChars.length; i++) {
            chars[position++] = specialChars[i];
        }
        //String[] myArray
        HashSet<String> passwords = getGuid(chars, 10000, 32);
        File myOutFile = new File("./src/resources/testy.txt");
        try {
            FileWriter fileWriter = new FileWriter(myOutFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(String s : passwords) {
                System.out.println(s);
                printWriter.write(s + "\n");
            }
            printWriter.print("");
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashSet<String> getGuid(char[] inputChars, int count, int len) {
        char[] shuffledChars = shuffle(inputChars);
        Random random = new Random();
        String returnStringArray;
        HashSet<String> returnSet = new HashSet<>();
        for (int currEntry =0; currEntry < count; ) {
            returnStringArray = "";
            for (int currLen = 0; currLen < len; currLen++) {
                returnStringArray += shuffledChars[random.nextInt(shuffledChars.length)];
            }
            if (returnSet.add(returnStringArray)) {
                currEntry++;
            }
        }

        return returnSet;
    }

    private static char[] shuffle(char[] input) {
        char[] shuffled = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            shuffled[i] = input[i];
        }
        char tempChar;
        int randomPos;
        Random random = new Random();

        for (int pos = 0; pos < input.length; pos++) {
            randomPos = random.nextInt(input.length);
            tempChar = shuffled[randomPos];
            shuffled[randomPos] = shuffled[pos];
            shuffled[pos] = tempChar;
        }
        return shuffled;
    }
}
