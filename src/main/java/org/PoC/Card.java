package org.PoC;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable{

    private String word1;
    private String word2;
    private long lastRepeat; // number of minutes passed from LocalDateTime.MIN
    private int learnIndex; // index from 0 to 10

    public Card(){
        word1 ="";
        word2 ="";
        lastRepeat = Duration.between(LocalDateTime.MIN,LocalDateTime.now()).toMinutes();
        learnIndex = 0;
    }

    public Card(String word1, String word2){
        this.word1 =word1;
        this.word2 =word2;
        lastRepeat = Duration.between(LocalDateTime.MIN,LocalDateTime.now()).toMinutes();
        learnIndex = 0;
    }

    public Card(String word1, String word2,long lastRepeat, int learnIndex){
        this.word1 =word1;
        this.word2 =word2;
        this.lastRepeat = lastRepeat;
        this.learnIndex = learnIndex;
    }


    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        words.add(word1);
        words.add(word2);
        return words;
    }

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWords(String word1,String word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    public long getLastRepeat() {
        return lastRepeat;
    }

    public void setLastRepeat(long lastRepeat) {
        this.lastRepeat = lastRepeat;
    }

    public int getLearnIndex() {
        return learnIndex;
    }

    public void setLearnIndex(int learnIndex) {
        this.learnIndex = learnIndex;
    }

    @Override
    public int compareTo(Object object) {
        if (object instanceof Card) {
            Card card1 = (Card) object;
            if (card1.getLastRepeat() < this.lastRepeat) {
                return 1;
            } else if (card1.getLastRepeat() > this.lastRepeat) {
                return -1;
            } else {
                return 0;
            }
        }else {
            throw new RuntimeException("Object is not a Card");
        }
    }
}
