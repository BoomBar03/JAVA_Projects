package model;

import java.time.LocalDate;

public class AudioBook extends Book {

    private int runTime;

    public int getRunTime() {
        return runTime;
    }


    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return String.format("Book ID: %d | Author: %s | Title: %s | RunTime: %d | Published Date: %s", getId(), getAuthor(), getTitle(), runTime, getPublishedDate());
    }

}
