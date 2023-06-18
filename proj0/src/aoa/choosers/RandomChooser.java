package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern = "";

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        /** read words from file */
        List<String> allWords = FileUtils.readWords(dictionaryFile);

        /** check if required length is valid */
        if (wordLength < 1) { throw new IllegalArgumentException("Length must be greater than 0"); }

        /** creates new list to contain words with desired length */
        List<String> possibleWords = FileUtils.readWordsOfLength(dictionaryFile, wordLength);

        /** check if there is at least 1 possible word */
        if (possibleWords.isEmpty()) { throw new IllegalStateException("There are no possible words of that length"); }

        int chosenIndex = StdRandom.uniform(possibleWords.size());
        chosenWord = possibleWords.get(chosenIndex);

        for (int i = 0; i < wordLength; i++) { pattern += "-"; }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        String temp = "";
        int count = 0;
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == letter) { count++; temp += letter; }
            else if (pattern.charAt(i) != '-') { temp += pattern.charAt(i); }
            else { temp += "-"; }
        }
        pattern = temp;
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }

    /**public static void main(String[] args) {
        RandomChooser test = new RandomChooser(4, "data/sorted_scrabble.txt");
        System.out.println(test.getWord());
        System.out.println(test.getPattern());
    }*/
}
