package files.Panes;

import files.App;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BlockerPane extends Pane {
    private static final BlockerPane instance = new BlockerPane();
    private static boolean isVisible = false;

    private BlockerPane() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0, 0.25), new CornerRadii(0), new Insets(0))));

        //this.setManaged(false);
        this.prefWidthProperty().bind(App.getPrimaryStage().widthProperty());
        this.prefHeightProperty().bind(App.getPrimaryStage().heightProperty());

        this.setVisible(isVisible);
        this.setPickOnBounds(isVisible);
    }

    public static BlockerPane getInstance() {
        return instance;
    }

    public static void setVisibleState(boolean state) {
        isVisible = state;
        instance.setVisible(state);
        instance.setPickOnBounds(state);
        instance.toFront();

    }
}

