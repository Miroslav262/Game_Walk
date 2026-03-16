package files.Panes;

import javafx.scene.layout.StackPane;

public class Game extends StackPane {
    private static Game instance;
    private static boolean isVisible = false;

    public static Game getInstance(){
        return instance;
    }

    public static void createNewGame(){

    }


    public static void setVisibleState(boolean state){
        isVisible = state;
        instance.setVisible(isVisible);
        instance.toFront();
    }
    public static void show(){
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();

    }

    public void hide(){
        instance.setVisible(false);
    }
}
