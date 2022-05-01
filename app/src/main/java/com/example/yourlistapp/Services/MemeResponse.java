package com.example.yourlistapp.Services;


public class MemeResponse {

    private String url;
    private String author;

    public Meme getMeme() {
        return new Meme(url, author);
    }
}
