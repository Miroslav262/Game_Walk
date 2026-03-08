package files.WayElements;

import java.util.List;

import files.Player;
import files.PlayerController;
import javafx.scene.layout.Pane;
import main.java.DB.DBController;
public class Way {
    private List<WayElement> elements;
    private PlayerController playerController;
    private Pane gameRoot;
    public Way(Pane gameRoot, DBController dbController, PlayerController playerController){
        try{
            elements = WayGenerator.genWay("src/main/java/files/config.yaml");
            this.playerController = playerController;
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

    public void doStep(int steps){
        Player player = playerController.getcurrentplayer();
        if(steps<0){
            if(player.getPosition()+steps < 0 ){
                playerController.getcurrentplayer().setPosition(0);
            }
            else{
                playerController.getcurrentplayer().setPosition(player.getPosition()+steps);
            }
        }
        else{
            if(player.getPosition()+steps >= elements.size()){
                playerController.getcurrentplayer().setPosition(elements.size()-1);
            }
            else{
                playerController.getcurrentplayer().setPosition(player.getPosition()+steps);
            }
            gameRoot.fireEvent(elements.get(playerController.getcurrentplayer().getPosition()).getEvent());
        }
    }


}
