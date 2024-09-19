package model;

/**
 * Represents a Mid Laner player in the League of Legends game.
 * Inherits from the LolPlayer class.
 */
public class MidLaner extends LolPlayer {
    /**
     * The summoner spell used by Mid Laner players.
     */
    public static final String SUMMONER = "ignite";

    private String mainChampion;

    /**
     * Constructs a MidLaner object with the specified summoner name, name, rank, level, and main champion.
     * 
     * @param summonerName the summoner name of the Mid Laner player
     * @param name the name of the Mid Laner player
     * @param rank the ranked rank of the Mid Laner player
     * @param level the level of the Mid Laner player
     * @param mainChampion the main champion of the Mid Laner player
     */
    public MidLaner(String summonerName, String name, RankedRank rank, int level, String mainChampion) {
        super(summonerName, name, Role.MID, rank, level);
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the main champion of the Mid Laner player.
     * 
     * @return the main champion of the Mid Laner player
     */
    public String getMainChampion() {
        return mainChampion;
    }

    /**
     * Sets the main champion of the Mid Laner player.
     * 
     * @param mainChampion the main champion to be set
     */
    public void setMainChampion(String mainChampion) {
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the summoner spell used by Mid Laner players.
     * 
     * @return the summoner spell used by Mid Laner players
     */
    public String getSummoner() {
        return SUMMONER;
    }
}