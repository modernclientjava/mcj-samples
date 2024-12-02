package org.modernclients.fundamentals.model;

public record Person (String firstname, String lastname, String notes) {
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}