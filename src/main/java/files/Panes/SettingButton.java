package files.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Screen;



public class SettingButton extends Button {
    public SettingButton(EventHandler<ActionEvent> eventHandler){
        super();

        this.setBackground(new Background(
                new BackgroundFill(Color.web("#008A00"), new CornerRadii(5), Insets.EMPTY)
        ));

        this.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight() * 0.075);
        this.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() * 0.075);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));

        this.setOnAction(eventHandler);

        ImageView imageView = new ImageView("/images/OptionMenuImage.png");
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(this.getMaxHeight() * 0.6);

        this.setGraphic(imageView);
    }
}

