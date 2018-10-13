package oleg.podolian;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AnagramTest {

    private final Anagram anagram = new Anagram();

    @Test
    public void whenEmptyOrNull_thenReturnEmpty() {
        assertEquals("", anagram.createAnagram(null));
        assertEquals("", anagram.createAnagram(""));
    }

    @Test
    public void testOnlyLettersSingleWord() {
        String source = "abcdef";
        String expected = "fedcba";
        assertEquals(expected, anagram.createAnagram(source));
    }

    @Test
    public void testOnlyLettersButDifferentWords() {
        String source = "abcdef hybris java";
        String expected = "fedcba sirbyh avaj";
        assertEquals(expected, anagram.createAnagram(source));
    }

    @Test
    public void testOnlyLettersButDifferentWordsWithSpaces() {
        String source =   " abcdef hybris    java   ";
        String expected = " fedcba sirbyh    avaj   ";
        assertEquals(expected, anagram.createAnagram(source));
    }

    @Test
    public void testAnySymbolsSingleWord() {
        String source =   "a$b%c*&d_e@f";
        String expected = "f$e%d*&c_b@a";
        assertEquals(expected, anagram.createAnagram(source));
    }

    @Test
    public void testOnlySpaces() {
        String source =   "    ";
        String expected = "    ";
        assertEquals(expected, anagram.createAnagram(source));
    }

    @Test
    public void testAnySymbolsButDifferentWords() {
        String source = "a&%bcd&ef hyb~ris ja*va";
        String expected = "f&%edc&ba sir~byh av*aj";
        assertEquals(expected, anagram.createAnagram(source));
    }

}