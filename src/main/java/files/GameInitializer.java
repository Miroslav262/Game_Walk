package files;

import javafx.scene.layout.StackPane;

public class GameInitializer extends StackPane {
    private static GameInitializer instance;

    public static GameInitializer getGameRoot(){
        return instance;
    }

    private GameInitializer(){

    }
}
