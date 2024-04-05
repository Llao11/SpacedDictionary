package org.PoC;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Card {

    private String word1;
    private String word2;
    private LocalTime lastRepeat;
    private int learnIndex; // index from 0 to 10

    public Card(){
        word1 ="";
        word2 ="";
        lastRepeat = LocalTime.MIN;
        learnIndex = 0;
    }

    public Card(String word1, String word2){
        this.word1 =word1;
        this.word2 =word2;
        lastRepeat = LocalTime.MIN;
        learnIndex = 0;
    }


    public List<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        words.add(word1);
        words.add(word2);
        return words;
    }

    public void setWords(String word1,String word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    public LocalTime getLastRepeat() {
        return lastRepeat;
    }

    public void setLastRepeat(LocalTime lastRepeat) {
        this.lastRepeat = lastRepeat;
    }

    public int getLearnIndex() {
        return learnIndex;
    }

    public void setLearnIndex(int learnIndex) {
        this.learnIndex = learnIndex;
    }
}
