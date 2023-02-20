package com.testspring.spring.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection = "course")
public class Course {
    @Id
    private String id;

    @NotBlank(message = "title is mandatory")
    @NotNull(message = "title is mandatory")
    private String title;

    @NotBlank(message = "durationInMonth is mandatory")
    @NotNull(message = "durationInMonth is mandatory")
    @Pattern(regexp = "^[0-9] month$", message = "durationInMonth should be like n month")
    private String durationInMonth;

    @NotNull(message = "amount is mandatory")
    @Min(100)
    private int amount;

    private String description;
    private List<String> languageRequired;
    private boolean isTrending=true;

    public Course(String title, String durationInMonth, int amount, String description, List<String> languageRequired, boolean isTrending) {
        this.title = title;
        this.durationInMonth = durationInMonth;
        this.amount = amount;
        this.description = description;
        this.languageRequired = languageRequired;
        this.isTrending = isTrending;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLanguageRequired() {
        return this.languageRequired;
    }

    public void setLanguageRequired(List<String> languageRequired) {
        this.languageRequired = languageRequired;
    }

    public boolean getIsTrending() {
        return this.isTrending;
    }

    public void setIsTrending(boolean isTrending) {
        this.isTrending = isTrending;
    }

    public String getDurationInMonth() {
        return this.durationInMonth;
    }

    public void setDurationInMonth(String durationInMonth) {
        this.durationInMonth = durationInMonth;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }    


    // @Override
    // public String toString() {
    //     return "{" +
    //             " title='" + getTitle() + "'" +
    //             ", durationInMonth='" + getDurationInMonth() + "'" +
    //             ", amount='" + getAmount() + "'" +
    //             ", description='" + getDescription() + "'" +
    //             ", languageRequired='" + getLanguageRequired() + "'" +
    //             "}";
    // }

    // @Override
    // public String toString() {
    //     return "{" +
    //             " id='" + getId() + "'" +
    //             ", title='" + getTitle() + "'" +
    //             ", description='" + getDescription() + "'" +
    //             "}";
    // }

}
