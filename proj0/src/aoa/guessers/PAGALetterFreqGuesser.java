package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    public Map<Character, Integer> getFreqMapThatMatchesPattern(String pattern, List<Character> guesses){
        List<String> validWords = new ArrayList<>();
        Map<Character, Integer> validFreqMap = new TreeMap();
        for (String word: words){
            int check = 0;
            for (int i = 0; i < pattern.length(); i++){
                char currentChar = pattern.charAt(i);
                char wordChar = word.charAt(i);
                if (word.length() != pattern.length()) { check = -1; break;}
                else if (currentChar == '-' && guesses.contains(wordChar)) { check = -1; break;}
                else if (currentChar != '-' && wordChar != currentChar){ check = -1; break;}
            }
            if (check == 0) { validWords.add(word); }
        }
        for (String validWord: validWords){
            for (int j = 0; j < validWord.length(); j++) {
                if (validFreqMap.containsKey(validWord.charAt(j))) {
                    int count = validFreqMap.get(validWord.charAt(j));
                    validFreqMap.put(validWord.charAt(j), count+1);
                }
                else { validFreqMap.put(validWord.charAt(j), 1); }
            }
        }
        return validFreqMap;
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        int max = 0;
        char maxChar = '?';
        Map<Character, Integer> validFreqMap = this.getFreqMapThatMatchesPattern(pattern, guesses);
        for (Character letter: validFreqMap.keySet()){
            if (!guesses.contains(letter) && validFreqMap.get(letter) > max){
                max = validFreqMap.get(letter);
                maxChar = letter;
            }
        }
        return maxChar;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
