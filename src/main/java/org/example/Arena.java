package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Arena {
    private int totalPlayers;

    private Map<Integer, Player> players;

    public Arena() {
        this.totalPlayers = 0;
        this.players = new HashMap<>();
        System.out.println("--------Welcome to the Arena--------\n");
    }

//    method to roll the dice (gives a random number between 1 and 6)
    public int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Roll a die (1-6)
    }

//    method to check if a Player is present in the arena
    public boolean isPresent(int id){
        return players.containsKey(id);
    }

//    method to get the number of players in the arena
    public int getPlayerCount(){
        return players.size();
    }

//    method to add a player in the arena
    public int addPlayer(String name, int health, int strength, int attack){
        if(health <= 0){
            System.out.println("Health can't be a negative number!");
            return -1;
        }
        if (strength <= 0) {
            System.out.println("Strength can't be a negative number!");
            return -1;
        }
        if (attack <= 0) {
            System.out.println("Attack can't be a negative number!");
            return -1;
        }

        int id = totalPlayers;
        Player newPlayer = new Player(id, name, health, strength, attack);
        players.put(id, newPlayer);
        totalPlayers++;

        return id;
    }

//    method to delete a player from the arena
    public void deletePlayer(int id){
        if(players.containsKey(id)){
            Player player = players.get(id);
            System.out.println(player.getName() + " is no longer alive!\n");
            players.remove(id);
        }else{
            System.out.println("No player with this id exists!\n");
        }
    }

//    method to display all the players present in the arena
    public void displayPlayers() {
        // Header
        System.out.printf("| %-5s | %-20s | %-8s | %-10s | %-6s |%n", "Id", "Name", "Health", "Strength", "Attack");
        System.out.println("|-------|----------------------|----------|------------|--------|");

        // Player entries
        for (Map.Entry<Integer, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            System.out.printf("| %-5d | %-20s | %-8d | %-10d | %-6d |%n", entry.getKey(), player.getName(), player.getHealth(), player.getStrength(), player.getAttack());
        }

        System.out.println(); // Extra line for spacing
    }


    //    method to start a battle
    public Map<String, Integer> battle(int idFirst, int idSecond){
        if(idFirst == idSecond){
            System.out.println("Id's cannot be the same for both players!\n");
            return new HashMap<>(); // returning an empty hashmap
        }else if(!players.containsKey(idFirst)){
            System.out.println("No player with id = " + idFirst + " exists!\n");
            return new HashMap<>();
        }else if(!players.containsKey(idSecond)){
            System.out.println("No player with id = " + idSecond + " exists!\n");
            return new HashMap<>();
        }else{
            Player attacker = players.get(idFirst);
            Player defender = players.get(idSecond);
            System.out.printf("\n__________%s V/S %s__________\n", attacker.getName(), defender.getName());

            if(defender.getHealth() < attacker.getHealth()){
                Player temp = attacker;
                attacker = defender;
                defender = temp;
            }

            while (defender.getHealth() > 0){
                int attackingPower = attacker.getAttack() * rollDice();
                int defendingPower = defender.getStrength() * rollDice();

                System.out.printf("%s hits %s with power = %d%n", attacker.getName(), defender.getName(), attackingPower);

                System.out.printf("%s defends with power = %d%n", defender.getName(), defendingPower);

                if(attackingPower > defendingPower){
                    defender.setHealth(defender.getHealth() - (attackingPower - defendingPower));
                    defender.setHealth(Math.max(0, defender.getHealth()));
                }

                System.out.printf("%s's health: %d%n", defender.getName(), defender.getHealth());

                if(defender.getHealth() > 0){
                    Player temp = attacker;
                    attacker = defender;
                    defender = temp;
                }
            }

            Map<String, Integer> battleResult = new HashMap<>();
            battleResult.put("winner", attacker.getId());
            battleResult.put("loser", defender.getId());
            System.out.printf("%s has won the game!!!%n", attacker.getName());
            deletePlayer(defender.getId());

            return battleResult;
        }
    }
}