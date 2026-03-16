package files;

import java.util.ArrayList;
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

    public static void createInstance(){
        instance = new PlayerController(new ArrayList<>());
    }
    public static void createInstance(List<Player> players){
        instance = new PlayerController(players);
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

    public List<Player> getPlayers() {
        return players;
    }

    public static void totalSwap() {
        List<Player> players = instance.players;
        List<Integer> positions = players.stream()
                .map(Player::getPosition)
                .toList();

        List<Integer> shuffled = new ArrayList<>(positions);
        Collections.shuffle(shuffled);

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPosition(shuffled.get(i));
        }
    }
    public static Player getForName(String name){
        for(Player player : getInstance().players){
            if(player.getName() == name){
                return player;
            }
        }
        return null;
    }

}

