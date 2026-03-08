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
    public Player getcurrentplayer(){
        return players.get(current);
    }
}
