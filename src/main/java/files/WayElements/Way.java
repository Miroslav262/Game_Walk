package files.WayElements;

import java.util.List;

import files.Events.PassMotion;
import files.GameDrawer;
import files.Player;
import files.PlayerController;
import files.WhoNowTurnLabel;
import javafx.scene.layout.Pane;

public class Way {
    private static Way instance;

    public static Way getInstance(){
        return  instance;
    }

    public static void createNewWay(Pane gameRoot){
        instance = new Way(gameRoot);
    }

    private List<WayElement> elements;
    private Pane gameRoot;
    private Way(Pane gameRoot){
        try{
            elements = WayGenerator.genWay("src/main/java/files/config.yaml");
            for(WayElement w: elements){
                System.out.println(w);
            }

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



    public void doStep(int steps) {
        Player player = PlayerController.getInstance().getCurrentPlayer();

        int newPos = player.getPosition() + steps;
        if (newPos >= elements.size()) {
            player.setPosition(elements.size() - 1);
        } else {
            player.setPosition(newPos);
        }
        GameDrawer.getInstance().draw();

        gameRoot.fireEvent(elements.get(player.getPosition()).getEvent());

        PlayerController.getInstance().toNext();
        PlayerController.getInstance().updateState();
        WhoNowTurnLabel.updateLabel();
        GameDrawer.getInstance().draw();
    }
}
