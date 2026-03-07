package files.WayElements;


import files.PlayerController;

public class RandSwapAllPlayersElement implements WayElement {

    private PlayerController playerController;

    public RandSwapAllPlayersElement(PlayerController playerController){
        this.playerController = playerController;
    }

    @Override
    public void action() {
        //перемешивает всех игроков
    }
}
