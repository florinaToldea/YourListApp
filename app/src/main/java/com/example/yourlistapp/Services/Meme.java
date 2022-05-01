package com.example.yourlistapp.Services;


public class Meme {

    private String url;
    private String author;

    public Meme() {}
    public Meme(String url, String author) {
        this.url = url;
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Meme{" +
                "url='" + url + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
