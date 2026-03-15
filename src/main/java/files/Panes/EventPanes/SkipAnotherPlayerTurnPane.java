package files.Panes.EventPanes;

import files.Events.SkipAnotherPlayerTurnEvent;
import files.GameDrawer;
import files.Panes.BlockerPane;
import files.Player;
import files.PlayerController;
import files.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class SkipAnotherPlayerTurnPane extends StackPane {
    private static final SkipAnotherPlayerTurnPane instance = new SkipAnotherPlayerTurnPane();

    private final Label label;
    private final VBox modalPane;
    private Player skippingPlayer = null;

    private SkipAnotherPlayerTurnPane() {

        modalPane = new VBox(10);
        modalPane.setBackground(new Background(new BackgroundFill(Utils.myGreenColor, new CornerRadii(10), new Insets(0))));

        modalPane.setVisible(true);
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        modalPane.setAlignment(Pos.CENTER);



        label = new Label("Игрок ??? выбирает, кто пропускает ход");
        label.setFont(Font.font("Comic Sans MS", 20));

        Button button = new Button("Продолжить");
        button.setBackground(new Background(new BackgroundFill(Color.web("#2CA90B"), new CornerRadii(5), new Insets(0))));
        button.setFont(Font.font("Comic Sans MS", 20));
        button.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));

        ComboBox <String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Выберите пропускающего ход");
        for(Player player : PlayerController.getInstance().getPlayers()){
            comboBox.getItems().add(player.getName());
        }

        comboBox.setOnAction(e -> {
            String selected = comboBox.getValue();
            if(selected != null){
                instance.skippingPlayer = PlayerController.getForName(selected);
            }
        });




        button.setOnAction(e -> {
            if(instance.skippingPlayer == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Выберите игрока");
                alert.showAndWait();
            }
            else {
                hide();
                BlockerPane.setVisibleState(false);

                SkipAnotherPlayerTurnPane.getInstance().skippingPlayer.passMation();
                SkipTurnPane.show(SkipAnotherPlayerTurnPane.getInstance().skippingPlayer);

                GameDrawer.getInstance().draw();
            }

        });

        Image image = new Image("/images/SkipAnotherPlayerTurn.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.25);

        modalPane.getChildren().addAll(label,imageView, comboBox ,button);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }

    public static SkipAnotherPlayerTurnPane getInstance() {
        return instance;
    }

    public static void show() {
        instance.label.setText("Игрок " + PlayerController.getInstance().getCurrentPlayer().getName() + " выбирает, кто пропускает ход");
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();
    }

    public static void hide() {
        instance.setVisible(false);
    }
}
