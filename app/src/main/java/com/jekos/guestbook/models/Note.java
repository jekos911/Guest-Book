package com.jekos.guestbook.models;

/**
 * Created by жекос on 12.04.2018.
 */

public class Note {
    private int id;
    private String firstName;
    private String lastName;

    public Note(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
