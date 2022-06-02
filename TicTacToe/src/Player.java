package src;

import java.util.UUID;

public class Player{
    private final String name;
    private String piece;
    private final String ID;

    public Player(String name,String piece){
        this.name = name;
        this.piece = piece;
        ID = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }


    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getID() {
        return ID;
    }
}