package files;

import java.util.Collections;
import java.util.List;

public class PlayerController {

    private static PlayerController instance;

    private final List<Player> players;
    private int current;

    private PlayerController(List<Player> players) {
        this.players = players;
        Collections.shuffle(players);
        this.current = 0;
    }

    public static PlayerController createInstance(List<Player> players) {
        if (instance == null) {
            instance = new PlayerController(players);
        }
        return instance;
    }

    public static PlayerController getInstance() {
        if (instance == null) {
            throw new IllegalStateException("PlayerController is not initialized!");
        }
        return instance;
    }

    public Player getNext() {
        current = (current + 1 == players.size()) ? 0 : current + 1;
        return players.get(current);
    }

    public Player getCurrentPlayer() {
        return players.get(current);
    }
}

