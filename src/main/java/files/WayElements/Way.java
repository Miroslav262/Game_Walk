package files.WayElements;

import java.util.List;

import files.Events.PassMotion;
import files.GameDrawer;
import files.Player;
import files.PlayerController;
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



    public void doStep(int steps){
        Player player = PlayerController.getInstance().getCurrentPlayer();

        if(player.isPassMotion()){
            player.continueMotion();
            gameRoot.fireEvent(new PassMotion(PassMotion.TYPE, player));
        }
        else{
            if(player.getPosition()+steps >= elements.size()){
                PlayerController.getInstance().getCurrentPlayer().setPosition(elements.size()-1);
            }
            else{
                PlayerController.getInstance().getCurrentPlayer().setPosition(player.getPosition()+steps);
            }

            gameRoot.fireEvent(elements.get(PlayerController.getInstance().getCurrentPlayer().getPosition()).getEvent());
        }
        Player next = PlayerController.getInstance().getNext();

        System.out.println("Ходил: " + player);
        System.out.println("Следующим ходит: " + next);
        GameDrawer.getInstance().draw();
    }

    public void doBackSteps(int steps){
        Player player = PlayerController.getInstance().getCurrentPlayer();
        if(player.getPosition()-steps < 0 ){
            PlayerController.getInstance().getCurrentPlayer().setPosition(0);
        }
        else{
            PlayerController.getInstance().getCurrentPlayer().setPosition(player.getPosition()-steps);
        }
    }


}
