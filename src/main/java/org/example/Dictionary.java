package org.example;

import java.util.ArrayList;

public class Dictionary {
    private String name;

    private ArrayList<Card> cards;

    public Dictionary(String name){
        this.name=name;
    }

    public boolean containCard(Card card){
        return cards.contains(card);
    }
    public void addCard(Card card){
        cards.add(card);
    }
    public void addCard(String word1, String word2){
        cards.add(new Card(word1,word2));
    }
    public void removeCard(Card card){
        cards.remove(card);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
