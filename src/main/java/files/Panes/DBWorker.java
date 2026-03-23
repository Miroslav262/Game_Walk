package files.Panes;


import files.DB.DBController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class DBWorker extends StackPane {
    private static DBWorker instance = new DBWorker();

    public static DBWorker getInstance(){
        return instance;
    }

    private DBWorker(){

        VBox vbox = new VBox();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        hBox.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, new CornerRadii(7), new Insets(0))));

        InputDBForm inputDBForm = new InputDBForm(DBController.getInstance());
        WatchingDB watchingDB = new WatchingDB(DBController.getInstance());

        hBox.getChildren().add(inputDBForm);
        hBox.getChildren().add(watchingDB);

        vbox.getChildren().add(hBox);
        vbox.setSpacing(20);
        vbox.getChildren().add(new StyledButton("Назад", 25, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenuPane.show();
                DBWorker.hide();
            }
        }));
        vbox.setAlignment(Pos.CENTER);
        vbox.setFillWidth(false);

        getChildren().add(vbox);


        this.setVisible(false);
    }

    public static void show() {
        instance.setVisible(true);
        instance.toFront();
    }

    public static void hide(){
        instance.setVisible(false);
    }

}
