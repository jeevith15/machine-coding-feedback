package Models;

import java.util.UUID;

public class Player{
    private String name;
    private String id;

    public Player(String name){
        this.name=name;
        this.id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}