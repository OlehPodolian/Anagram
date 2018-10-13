package oleg.podolian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Anagram {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z]+$");
    private static final Pattern WHITE_SPACES = Pattern.compile("\\s*");
    private static final String EMPTY = "";
    private static final String SPACE = " ";

    /**
     * Reverses the string input according to the following scenarios:
     * - when input is null or empty then simply returns empty string
     * - when input contains only spaces then no processing is needed and it is returned as it is
     * - when input is a valid single word (only letters) then it gets reversed by StringBuffer
     * - in other cases reversion algorithm is applied
     *
     * @param input - raw input
     * @return result reversing processing
     */
    public String createAnagram(String input) {
        if (input == null || input.isEmpty()) {
            return EMPTY;
        } else if (containsOnlySpaces(input)) {
            return input;
        } else if (isSingleWord(input)) {
            return new StringBuffer(input).reverse().toString();
        }
        return resolveManyWords(input);
    }

    /**
     * Check if string has only letters (characters) to use simple reverse
     *
     * @param input - raw (including unprocessed) input
     * @return result of matching the pattern
     */
    private boolean isSingleWord(String input) {
        Matcher matcher = PATTERN.matcher(input);
        return matcher.matches();
    }

    /**
     * Check if string has only spaces to skip processing
     *
     * @param input - raw (including unprocessed) input
     * @return result of matching the pattern
     */
    private boolean containsOnlySpaces(String input) {
        Matcher matcher = WHITE_SPACES.matcher(input);
        return matcher.matches();
    }

    /**
     * Reverses single word as to following scenarios:
     * - when input is empty then empty is returned
     * - when input is a valid single word (only letters) then it gets reversed by StringBuffer
     * - in other cases reversion algorithm is applied
     *
     * @param input - raw input
     * @return result reversing processing of single word
     */
    private String reverseWord(String input) {
        if (input.equals(EMPTY)) {
            return EMPTY;
        } else if (isSingleWord(input)) {
            return new StringBuffer(input).reverse().toString();
        }
        int index = 0;
        char[] symbols = input.toCharArray();
        Map<Integer, Character> nonLetters = new TreeMap<>();
        LinkedList<String> letters = new LinkedList<>();
        for (char ch : symbols) {
            if (Character.isAlphabetic(ch)) {
                letters.offerFirst(String.valueOf(ch));
            } else {
                nonLetters.put(index, ch);
            }
            index++;
        }
        final StringBuffer container = new StringBuffer(String.join(EMPTY, letters.toArray(new String[]{})));
        nonLetters.forEach((key, value) -> container.insert(key.intValue(), value.charValue()));
        return container.toString();
    }

    /**
     * When input contains spaces it gets divided to separate words but start and
     * combined spaces are added to neighbouring word and ends with tail
     *
     * @param input - raw (unprocessed) input
     * @return String containing only spaces to add to the last word
     */
    private String resolveManyWords(String input) {
        String tail = getTail(input);
        List<String> elements = new ArrayList<>();
        Arrays
                .stream(input.split(SPACE))
                .forEach(elm -> elements.add(reverseWord(elm)));
        return String.join(SPACE, elements) + tail;
    }

    /**
     * When many words are separated by spaces they're treated separately but trailing spaces will
     * be trimmed by splitter
     *
     * @param input - raw (unprocessed) input
     * @return String containing only spaces to add to the last word
     */
    private String getTail(String input) {
        if (!input.endsWith(SPACE)) {
            return EMPTY;
        }
        final StringBuilder tail = new StringBuilder();
        while (input.endsWith(SPACE)) {
            tail.append(SPACE);
            input = input.substring(0, input.length() - 1);

        }
        return tail.toString();
    }
}
