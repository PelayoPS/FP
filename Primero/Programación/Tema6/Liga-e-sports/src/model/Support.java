package model;

/**
 * The Support class represents a support player in the League of Legends game.
 * It extends the LolPlayer class and adds additional properties and methods
 * specific to support players.
 */
public class Support extends LolPlayer {
    /**
     * The summoner spell used by support players.
     */
    public static final String SUMMONER = "exhaust";

    private String mainChampion;

    /**
     * Constructs a Support object with the specified summoner name, name, rank,
     * level, and main champion.
     * 
     * @param summonerName the summoner name of the support player
     * @param name         the name of the support player
     * @param rank         the ranked rank of the support player
     * @param level        the level of the support player
     * @param mainChampion the main champion played by the support player
     */
    public Support(String summonerName, String name, RankedRank rank, int level, String mainChampion) {
        super(summonerName, name, Role.SUPPORT, rank, level);
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the main champion played by the support player.
     * 
     * @return the main champion played by the support player
     */
    public String getMainChampion() {
        return mainChampion;
    }

    /**
     * Sets the main champion played by the support player.
     * 
     * @param mainChampion the main champion to be set
     */
    public void setMainChampion(String mainChampion) {
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the summoner spell used by support players.
     * 
     * @return the summoner spell used by support players
     */
    public String getSummoner() {
        return SUMMONER;
    }
}
