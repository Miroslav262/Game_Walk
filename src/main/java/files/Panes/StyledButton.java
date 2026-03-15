package files.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



public class StyledButton extends Button {

    public StyledButton(String str, int fontSize, EventHandler<ActionEvent> eventHandler){
        super(str);
        this.setBackground(new Background(
                new BackgroundFill(Color.web("#2CA90B"), new CornerRadii(5), Insets.EMPTY)
        ));
        this.setFont(Font.font("Comic Sans MS", fontSize));
        this.setOnAction(eventHandler);
    }
}
