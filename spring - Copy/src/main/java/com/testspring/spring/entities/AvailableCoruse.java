package main.java.com.testspring.spring.entities;

public class AvailableCoruse {
    private String courseName;
    private int amount;

    public AvailableCoruse(String courseName, int amount) {
        this.courseName = courseName;
        this.amount = amount;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
