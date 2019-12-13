package se.terhol.telenorapi.model;

import lombok.Data;

@Data
public class Greeting {

    int id;
    String message;

    public Greeting(int id) {
        this.id = id;
        this.message = String.format("Hi, userID %d", id);
    }
}
