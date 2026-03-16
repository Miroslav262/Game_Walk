package files.Panes;

import files.Panes.PlayerRegPaneComponents.PlayerInputComponent;
import files.Player;
import files.PlayerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegistrationPane extends StackPane {
    private static PlayerRegistrationPane instance;
    private static boolean isVisible = false;

    public static PlayerRegistrationPane getInstance(){
        return instance;
    }

    public PlayerRegistrationPane(){
        VBox vBox = new VBox();
        vBox.setSpacing(20);

        Label label = new Label("Главное меню");
        label.setFont(Font.font("Comic Sans MS", 30));

        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox();
        vBox1.setBackground(new Background(
                new BackgroundFill(Color.web("#14600C"), new CornerRadii(5), Insets.EMPTY)
        ));
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(10);

        StyledButton addPlayerBut = new StyledButton("Добавить игрока", 35, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vBox1.getChildren().add(vBox1.getChildren().size()-1, new PlayerInputComponent());
            }
        });

        vBox1.getChildren().addAll(new PlayerInputComponent(), new PlayerInputComponent(), new PlayerInputComponent(), addPlayerBut);

        vBox.getChildren().add(vBox1);

        StyledButton submitBut = new StyledButton("Продолжить", 35, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Node> resultNodes = vBox1.getChildren();
                if(resultNodes.size()<=3){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Слишком мало участников");
                    alert.show();
                    return;
                }
                for(int i = 0 ;i<resultNodes.size()-1; i++){
                    PlayerInputComponent current = (PlayerInputComponent)(resultNodes.get(i));
                    if(current.getColor() == null || current.getPlayerName() == null || current.getPlayerName().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Заполните пустые поля");
                        alert.show();
                        return;
                    }
                }

                resultNodes.removeLast();
                List<Player> result = new ArrayList<>();
                for(Node node : resultNodes){
                    PlayerInputComponent playerInputComponent = (PlayerInputComponent)node;
                    result.add(new Player(playerInputComponent.getPlayerName(), playerInputComponent.getColor()));
                }
                
                PlayerController.createInstance(result);

                Game.createNewGame();
                Game.show();
                hide();
            }
        });

        vBox.getChildren().add(submitBut);
        this.getChildren().add(vBox);
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
