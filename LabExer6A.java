package javaActivities;

import java.util.Random;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class LabExer6A {

    private static String filePath = "C:\\Users\\Administrator\\Documents\\VSCode\\java\\javaActivities\\TextFiles\\wordfile.txt";
    private static String word[]; 
    private static String wordChosen;
    private static char convertedLetters[];
    private static int attempts = 8;
    private static Random randomizer = new Random();
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        readTextFile(filePath);
        chooseWordFromFile();
        convertTheWordToSecret();

        while(attempts != 0) {
            System.out.println("Complete the incomplete word: " + String.valueOf(convertedLetters));
            System.out.println("You have " + attempts + " attempts remaining");
            System.out.print("Enter letter: ");
            char guess = userInput.next().charAt(0);

            if (!updateGuessedLetters(guess)) {
                attempts--;
                System.out.println("Incorrect guess!");
                continue;
            }
            if (checkIfWordIsGuessed()) {
                System.out.println("You did it! The word was: " + wordChosen);
                break;
            }
            if (attempts == 0) {
                System.out.println("You ran out of guesses! Severe skill issue! The word was: " + wordChosen);
                System.exit(0);
            }
        }
    }

    public static void readTextFile(String fileName) throws IOException{
        BufferedReader readFile = new BufferedReader(new FileReader(fileName)); 
        StringBuilder stringBuilder = new StringBuilder();
        String oneLineOfText;
            while ((oneLineOfText = readFile.readLine()) != null) {
                stringBuilder.append(oneLineOfText.trim()).append(" ");
            }
            word = stringBuilder.toString().split("\\s+");
    }

    private static void chooseWordFromFile() {
        wordChosen = word[randomizer.nextInt(0,4)];
    }

    private static void convertTheWordToSecret() {
        convertedLetters = new char[wordChosen.length()];
            for (int i = 0; i != wordChosen.length(); i++) {
                int randomAmount = randomizer.nextInt(0, wordChosen.length());
                if (Character.isLetter(wordChosen.charAt(i))) {
                    convertedLetters[i] = wordChosen.charAt(i);
                } 
                convertedLetters[randomAmount] = '?';
            }
    }

    private static boolean updateGuessedLetters(char guess) {
        boolean found = false;
        for (int i = 0; i < wordChosen.length(); i++) {
            if(wordChosen.charAt(i) == guess) {
                convertedLetters[i] = guess;
                found = true;
            }
        }
        return found;
    }

    private static boolean checkIfWordIsGuessed() {
        for(char c : convertedLetters) {
            if (c == '?') {
                return false;
            }
        }
        return true;
    }
}