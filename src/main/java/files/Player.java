package files;

import javafx.scene.paint.Color;

import java.util.Objects;

public class Player {
    private String name;
    private Color color;
    private int position;
    private boolean isPassMotion;

    public Player(String name, Color color){
        position = 0;
        this.color = color;
        this.name = name;
        this.isPassMotion = false;
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

    public boolean isPassMotion() {
        return isPassMotion;
    }

    public void passMation(){
        this.isPassMotion = true;
    }

    public void continueMotion(){
        this.isPassMotion = false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(color, player.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
