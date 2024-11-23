package org.example;

import java.util.Scanner;

public class Main{

    public static Scanner scanner = new Scanner(System.in);

//    method to take and format the input
    private static int inputInteger(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

//    method to take the Player details as input
    private static Player inputNewPlayerDetails() {
        System.out.print("Enter the player's name: ");
        String name = scanner.nextLine();

        int health = inputInteger(String.format("Enter %s's health: ", name));
        int attack = inputInteger(String.format("Enter %s's attack: ", name));
        int strength = inputInteger(String.format("Enter %s's strength: ", name));

        return new Player(-1, name, health, strength, attack); // ID will be assigned by Arena
    }

    public static void main(String[] args) {
        Arena arena = new Arena();
        while (true) {
            arena.displayPlayers();
            System.out.println("Options: \n\t1> Add new player\n\t2> Battle\n\t3> End game\n");
            int option = inputInteger("Enter your choice (integer): ");

            if (option == 1) {
                Player newPlayer = inputNewPlayerDetails();
                if (newPlayer != null) {
                    arena.addPlayer(newPlayer.getName(), newPlayer.getHealth(), newPlayer.getStrength(),
                            newPlayer.getAttack());
                }
            } else if (option == 2) {
                if (arena.getPlayerCount() < 2) {
                    System.out.println("There should be at least two players in the Arena.\nPlease add more players.\n");
                } else {
                    int idFirst = inputInteger("Enter the first player's id: ");
                    int idSecond = inputInteger("Enter the second player's id: ");
                    arena.battle(idFirst, idSecond);
                }
            } else {
                System.out.println("Bye Bye...\n");
                break;
            }

            System.out.println("\n----------------------------------------------------------------------------------------------\n");
        }
        scanner.close();
    }
}