package main;

import java.util.List;

public class PlayerController {
    private List<Player> players;
    private int current;

    public PlayerController(List<Player> players, int current){
        this.players = players;
        this.current= current;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrent() {
        return current;
    }

    public int toNext(){
        current = (current+1 == players.size()) ? 0 : current+1;
        return current;
    }

    public Player getPlayer(int current){
        return players.get(current);
    }


}
