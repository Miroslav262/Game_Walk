package files.Animations;

import files.Dice;
import files.App;
import files.Panes.BlockerPane;
import files.WayElements.Way;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class RollDiceAnimationPane extends StackPane {
    private static RollDiceAnimationPane instance = new RollDiceAnimationPane();

public static RollDiceAnimationPane getInstance(){
    return instance;
}
    private MediaPlayer mediaPlayer;
    private final MediaView mediaView;

    private RollDiceAnimationPane() {
        mediaView = new MediaView();
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(App.getPrimaryStage().widthProperty().multiply(0.3));
        mediaView.fitHeightProperty().bind(App.getPrimaryStage().heightProperty().multiply(0.3));


        getChildren().add(mediaView);
        setVisible(false);
    }

    public void play() {

        int roll = Dice.getInstance().roll();

        String path = getClass().getResource("/images/DiceAnim/" + roll + ".mp4").toExternalForm();
        Media media = new Media(path);

        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        setVisible(true);

        mediaPlayer.setOnEndOfMedia(() -> {
            setVisible(false);
            mediaPlayer.stop();
            Way.getInstance().doStep(roll);
            BlockerPane.setVisibleState(false);
        });

        mediaPlayer.play();
    }
}
