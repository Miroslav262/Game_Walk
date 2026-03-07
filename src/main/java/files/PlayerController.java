package files;

import java.util.Collections;
import java.util.List;

public class PlayerController {
    private List<Player> players;
    private int current;

    public PlayerController(List<Player> players){
        this.players = players;
        Collections.shuffle(players);
        this.current= 0;
    }

    public Player getNext(){
        current = (current+1 == players.size()) ? 0 : current+1;
        return players.get(current);
    }

    public void upgradeState(Player player){
        int id = players.indexOf(player);

        if(id != -1){
            players.set(id, player);
        }
    }
}
