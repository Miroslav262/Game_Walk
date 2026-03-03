package main;

import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;
    private int position;

    public Player(String name, Color color){
        position = 0;
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }
}
