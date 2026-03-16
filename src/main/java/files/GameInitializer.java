package files;

import files.Events.*;
import files.Panes.BlockerPane;
import files.Panes.EventPanes.*;
import files.Panes.GameMenuOptions;
import files.Panes.MainMenuPane;
import files.Panes.PlayerRegistrationPane;
import files.WayElements.Way;
import javafx.scene.layout.StackPane;

public class GameInitializer extends StackPane {
    private static GameInitializer instance = new GameInitializer();

    public static GameInitializer getGameRoot(){
        return instance;
    }

    private GameInitializer(){
        PlayerController.createInstance();

        QuestionPane.createInstance(this);

        this.addEventHandler(FinishEvent.TYPE, e -> {
            FinishPane.show(e.getPlayer());
        });
        this.addEventHandler(SkipTurnEvent.TYPE, e -> {
            SkipTurnPane.show(e.getPlayer());
        });
        this.addEventHandler(StepEvent.TYPE, e -> {
            Player player = e.getPlayer();
            int steps = e.getSteps();

            int newPos = player.getPosition() + steps;

            if (newPos >= Way.getInstance().getElements().size()) {
                FinishPane.show(player);
                return;
            }

            if (newPos < 0) newPos = 0;

            player.setPosition(newPos);

            StepPane.show(player, steps);
            e.consume();
        });

        this.addEventHandler(TotalSwapEvent.TYPE, e -> {
            TotalSwapPane.show();
        });

        this.addEventHandler(SkipAnotherPlayerTurnEvent.TYPE, e -> {
            SkipAnotherPlayerTurnPane.show();

        });

        this.addEventHandler(QuestionEvent.TYPE, e -> {
            QuestionPane.getInstance().show();
            BlockerPane.setVisibleState(true);

            e.consume();
        });



        this.getChildren().add(BlockerPane.getInstance());
        this.getChildren().add(SkipTurnPane.getInstance());
        this.getChildren().add(FinishPane.getInstance());
        this.getChildren().add(StepPane.getInstance());
        this.getChildren().add(TotalSwapPane.getInstance());
        this.getChildren().add(SkipAnotherPlayerTurnPane.getInstance());
        this.getChildren().add(QuestionPane.getInstance());
        //this.getChildren().add(Game.getInstance());
        this.getChildren().add(PlayerRegistrationPane.getInstance());
        this.getChildren().add(MainMenuPane.getInstance());
        this.getChildren().add(GameMenuOptions.getInstance());
    }
}
