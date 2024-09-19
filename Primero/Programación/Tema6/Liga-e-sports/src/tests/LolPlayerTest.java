package tests;




import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.LolPlayer;
import model.RankedRank;
import model.Role;

public class LolPlayerTest {

    @Test
    public void testLolPlayer() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals("Player1", player.getSummonerName());
        assertEquals("John Doe", player.getName());
        assertEquals(Role.TOP, player.getRole());
        assertEquals(RankedRank.GOLD, player.getRank());
        assertEquals(100, player.getLevel());
    }

    @Test
    public void testGetSummonerName() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals("Player1", player.getSummonerName());
    }

    @Test
    public void testGetName() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals("John Doe", player.getName());
    }

    @Test
    public void testGetRole() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals(Role.TOP, player.getRole());
    }

    @Test
    public void testGetRank() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals(RankedRank.GOLD, player.getRank());
    }

    @Test
    public void testGetLevel() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals(100, player.getLevel());
    }

    @Test
    public void testSetSummonerName() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        player.setSummonerName("Player2");
        assertEquals("Player2", player.getSummonerName());
    }

    @Test
    public void testSetName() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        player.setName("Jane Doe");
        assertEquals("Jane Doe", player.getName());
    }

    @Test
    public void testSetRole() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        player.setRole(Role.JUNGLE);
        assertEquals(Role.JUNGLE, player.getRole());
    }

    @Test
    public void testSetRank() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        player.setRank(RankedRank.PLATINUM);
        assertEquals(RankedRank.PLATINUM, player.getRank());
    }

    @Test
    public void testSetLevel() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        player.setLevel(150);
        assertEquals(150, player.getLevel());
    }

    @Test
    public void testToString() {
        LolPlayer player = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals("LolPlayer [summonerName=Player1, name=John Doe, role=TOP, rank=GOLD, level=100]",
                player.toString());
    }

    @Test
    public void testHashCode() {
        LolPlayer player1 = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        LolPlayer player2 = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    public void testEquals() {
        LolPlayer player1 = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        LolPlayer player2 = new LolPlayer("Player1", "John Doe", Role.TOP, RankedRank.GOLD, 100);
        assertEquals(player1, player2);
    }
}
