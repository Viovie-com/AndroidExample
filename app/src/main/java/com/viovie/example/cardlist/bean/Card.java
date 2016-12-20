package com.viovie.example.cardlist.bean;

import java.io.Serializable;

public class Card implements Serializable {
    public String title;
    public String content;

    public Card() {
    }

    public Card(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
