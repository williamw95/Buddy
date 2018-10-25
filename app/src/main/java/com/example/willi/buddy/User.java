package com.example.willi.buddy;

//User (To-Do) class object, stores the question title and URL which user got wrong from their quiz

public class User {
    private String title;
    private String URL;

    public User(String title, String URL) {
        this.title = title;
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
