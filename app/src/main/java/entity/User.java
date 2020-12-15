package entity;

public class User {
    private String email;
    private String name;
    private String password;
    private int acquainted;
    private int tested;
    private int learned;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
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

    public int getAcquainted() {
        return acquainted;
    }

    public void setAcquainted(int acquainted) {
        this.acquainted = acquainted;
    }

    public int getTested() {
        return tested;
    }

    public void setTested(int tested) {
        this.tested = tested;
    }

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }
}
