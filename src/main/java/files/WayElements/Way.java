package files.WayElements;

import java.util.List;

import files.Events.PassMotion;
import files.Player;
import files.PlayerController;
import javafx.scene.layout.Pane;
import jdk.jfr.Event;
import main.java.DB.DBController;
public class Way {
    private List<WayElement> elements;
    private PlayerController playerController;
    private Pane gameRoot;
    public Way(Pane gameRoot){
        try{
            elements = WayGenerator.genWay("src/main/java/files/config.yaml");
            for(WayElement w: elements){
                System.out.println(w);
            }

            this.playerController = PlayerController.getInstance();
            this.gameRoot = gameRoot;
            }
        catch (Throwable throwable){
            System.out.println(throwable);
            throw new RuntimeException("Ошибка инициализации пути");
        }
    }

    public List<WayElement> getElements(){
        return this.elements;
    }

    public PlayerController getPlayerController(){
        return this.playerController;
    }

    public void doStep(int steps){
        Player player = playerController.getCurrentPlayer();

        if(player.isPassMotion()){
            player.continueMotion();
            gameRoot.fireEvent(new PassMotion(PassMotion.TYPE, player));
        }
        else{
            if(player.getPosition()+steps >= elements.size()){
                playerController.getCurrentPlayer().setPosition(elements.size()-1);
            }
            else{
                playerController.getCurrentPlayer().setPosition(player.getPosition()+steps);
            }

            gameRoot.fireEvent(elements.get(playerController.getCurrentPlayer().getPosition()).getEvent());
        }
        Player next = playerController.getNext();

        System.out.println("Ходил: " + player);
        System.out.println("Следующим ходит: " + next);
    }

    public void doBackSteps(int steps){
        Player player = playerController.getCurrentPlayer();
        if(player.getPosition()-steps < 0 ){
            playerController.getCurrentPlayer().setPosition(0);
        }
        else{
            playerController.getCurrentPlayer().setPosition(player.getPosition()-steps);
        }
    }


}
