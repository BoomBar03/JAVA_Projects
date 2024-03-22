package model;

import java.time.LocalDate;

public class EBook extends Book {

    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String toString() {
        return String.format("Book ID: %d | Author: %s | Title: %s | Format: %s | Published Date: %s", getId(), getAuthor(), getTitle(), format, getPublishedDate());
    }
}
