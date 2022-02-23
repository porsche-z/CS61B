import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void  testIsPalindrome() {
        String nullWord = null;
        String oneWorld = "x";
        String trueEvenPalindrome = "qwerrewq";
        String trueOddPalindrome = "qwewq";
        String falseWord = "qwert";
        assertTrue(palindrome.isPalindrome(nullWord));
        assertTrue(palindrome.isPalindrome(oneWorld));
        assertTrue(palindrome.isPalindrome(trueEvenPalindrome));
        assertTrue(palindrome.isPalindrome(trueOddPalindrome));
        assertFalse(palindrome.isPalindrome(falseWord));
    }
    @Test
    public void testIsPalindromeByOne(){
        OffByOne cc = new OffByOne();
        String nullWord = null;
        String oneWorld = "x";
        String trueEvenPalindrome = "qwersdvp";
        String trueOddPalindrome = "qwevr";
        String falseWord1 = "qwert";
        String falseWord2 = "qwwq";
        assertTrue(palindrome.isPalindrome(nullWord, cc));
        assertTrue(palindrome.isPalindrome(oneWorld, cc));
        assertTrue(palindrome.isPalindrome(trueEvenPalindrome, cc));
        assertTrue(palindrome.isPalindrome(trueOddPalindrome, cc));
        assertFalse(palindrome.isPalindrome(falseWord1, cc));
        assertFalse(palindrome.isPalindrome(falseWord1, cc));
    }
}
