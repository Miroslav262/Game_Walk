package files.Panes;

import javafx.scene.layout.Pane;

public class BlockerPane extends Pane {
    private static BlockerPane instance= new BlockerPane();
    private static boolean isVisible = false;

    private BlockerPane(){
        this.setStyle("-fx-background-color: rgba(0,0,0,0.25);");
        this.setVisible(isVisible);
        this.setPickOnBounds(isVisible);
    }

    public static BlockerPane getInstance() {
        return instance;
    }

    public static boolean isItVisible(){
        return isVisible;
    }

    public static void setVisibleState(boolean state){
        isVisible = state;
        instance.setVisible(isVisible);
        instance.setPickOnBounds(isVisible);
    }

}
