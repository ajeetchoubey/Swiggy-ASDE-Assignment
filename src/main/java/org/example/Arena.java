package org.example;

import java.util.HashMap;
import java.util.Map;

public class Arena {
    private int totalPlayers;
    private Map<Integer, Player> players;

    public Arena() {
        this.totalPlayers = 0;
        this.players = new HashMap<>();
        System.out.println("--------Welcome to the Arena--------");
    }
}