import java.util.LinkedList;

public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word){
        if (word == null)
            return true;
        Deque originDeque = wordToDeque(word);
        LinkedList reverseDeque = new LinkedList();
        while (originDeque.isEmpty() == false){
            reverseDeque.addLast(originDeque.removeLast());
        }
        String reverse = "";
        for (int i = 0; i < word.length(); i++) {
            reverse += reverseDeque.removeFirst();
        }
        if(word.compareTo(reverse) == 0)
            return true;
        else
            return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word == null)
            return true;
        Deque originDeque = wordToDeque(word);
        LinkedList reverseDeque = new LinkedList();
        while (originDeque.isEmpty() == false){
            reverseDeque.addLast(originDeque.removeLast());
        }
        if(word.length()%2 !=0) {
            int middle = (word.length()) / 2;
            for (int i = 0; i < word.length(); i++) {
                if (i !=middle){
                    if (!cc.equalChars(word.charAt(i), (Character) reverseDeque.removeFirst()))
                        return false;
                }
                else
                    reverseDeque.removeFirst();
            }
            return true;
        }
        else{
            for (int i = 0; i < word.length(); i++) {
                if (!cc.equalChars(word.charAt(i), (Character) reverseDeque.removeFirst()))
                    return false;
            }
            return true;
        }
    }
}
