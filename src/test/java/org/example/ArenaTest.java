package org.example;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class ArenaTest {
    private Arena arena;

    @Before
    public void setUp() {
        arena = new Arena();
    }

    @Test
    public void testAddPlayerHealthCannotBeNegativeOrZero() {
        int id = arena.addPlayer("A", -2, 200, 100);
        assertEquals(-1, id);

        id = arena.addPlayer("A", 0, 200, 100);
        assertEquals(-1, id);
    }

    @Test
    public void testAddPlayerStrengthCannotBeNegativeOrZero() {
        int id = arena.addPlayer("A", 2, -200, 100);
        assertEquals(-1, id);

        id = arena.addPlayer("A", 10, 0, 100);
        assertEquals(-1, id);
    }

    @Test
    public void testAddPlayerAttackCannotBeNegativeOrZero() {
        int id = arena.addPlayer("A", 2, 200, -100);
        assertEquals(-1, id);

        id = arena.addPlayer("A", 10, 120, 0);
        assertEquals(-1, id);
    }

    @Test
    public void testNewlyAddedPlayerIsPresentInArena() {
        int id = arena.addPlayer("A", 100, 200, 100);
        assertTrue(arena.isPresent(id));
    }

    @Test
    public void testPlayerCountIncreasesAfterAddingNewPlayer() {
        int oldPlayerCount = arena.getPlayerCount();
        arena.addPlayer("A", 100, 200, 100);
        int newPlayerCount = arena.getPlayerCount();

        assertEquals(oldPlayerCount + 1, newPlayerCount);
    }

    @Test
    public void testDeletedPlayerIsNotPresentInArena() {
        int id = arena.addPlayer("A", 100, 200, 100);
        arena.deletePlayer(id);
        assertFalse(arena.isPresent(id));
    }

    @Test
    public void testPlayerCountDecreasesAfterDeletion() {
        int id = arena.addPlayer("A", 100, 200, 100);
        int oldPlayerCount = arena.getPlayerCount();

        arena.deletePlayer(id);
        int newPlayerCount = arena.getPlayerCount();

        assertEquals(oldPlayerCount - 1, newPlayerCount);
    }

    @Test
    public void testNonExistentPlayerCannotBeDeleted() {
        int id = arena.addPlayer("A", 100, 200, 100);

        int oldPlayerCount = arena.getPlayerCount();
        arena.deletePlayer(id + 123); // Attempt to delete a non-existent player
        int newPlayerCount = arena.getPlayerCount();

        assertEquals(oldPlayerCount, newPlayerCount);
    }

    @Test
    public void testBattleWithEmptyArena() {
        assertEquals(0, arena.battle(0, 1).size());
    }

    @Test
    public void testPlayersHaveSameId() {
        arena.addPlayer("A", 100, 200, 100);
        arena.addPlayer("B", 200, 300, 100);

        assertEquals(0, arena.battle(0, 0).size());
    }

    @Test
    public void testOneOfThePlayersIdDoesNotExist() {
        arena.addPlayer("A", 100, 200, 100);
        arena.addPlayer("B", 200, 300, 100);

        assertEquals(0, arena.battle(0, 10).size());
    }

    @Test
    public void testNormalBattle() {
        arena.addPlayer("A", 100, 200, 100);
        arena.addPlayer("B", 200, 300, 100);

        var possibleOutcomes = new int[][] { { 0, 1 }, { 1, 0 } };

        var res = arena.battle(0, 1);

        boolean outcomeFound = false;

        for (int[] outcome : possibleOutcomes) {
            if (outcome[0] == res.get("winner") && outcome[1] == res.get("loser")) {
                outcomeFound = true;
                break;
            }
        }

        assertTrue(outcomeFound);

        int winner = res.get("winner");

        // The winner should be present in the Arena
        assertTrue(arena.isPresent(winner));

        // The loser should not be present in the Arena
        assertFalse(arena.isPresent(res.get("loser")));
    }

    @Test
    public void testRollDiceReturnsNumberBetween1And6() {
        int result = arena.rollDice();
        assertTrue("The result should be between 1 and 6", result >= 1 && result <= 6);
    }
}