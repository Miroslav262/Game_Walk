package files.Panes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.java.DB.DBController;

public class DBWorker extends Pane {
    DBController dbController;

    public DBWorker(DBController dbController){
        this.dbController = dbController;

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        hBox.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, new CornerRadii(7), new Insets(0))));

        InputDBForm inputDBForm = new InputDBForm(dbController);
        WatchingDB watchingDB = new WatchingDB(dbController);

        hBox.getChildren().add(inputDBForm);
        hBox.getChildren().add(watchingDB);

        getChildren().add(hBox);
    }



}
