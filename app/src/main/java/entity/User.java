package entity;

import android.provider.UserDictionary;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String name;
    private String password;
    private List<Word> acquaintedWords;
    private List<Word> testedWords;
    private List<Word> matchedWords;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.acquaintedWords = new ArrayList<>();
        this.testedWords = new ArrayList<>();
        this.matchedWords = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Word> getAcquaintedWords() {
        return acquaintedWords;
    }

    public void setAcquaintedWords(List<Word> acquaintedWords) {
        this.acquaintedWords = acquaintedWords;
    }

    public List<Word> getTestedWords() {
        return testedWords;
    }

    public void setTestedWords(List<Word> testedWords) {
        this.testedWords = testedWords;
    }

    public List<Word> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<Word> matchedWords) {
        this.matchedWords = matchedWords;
    }
}
