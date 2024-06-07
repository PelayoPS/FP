package model;

/**
 * The TopLaner class represents a player who specializes in the top lane role
 * in the game League of Legends.
 * It extends the LolPlayer class and adds additional functionality specific to
 * top laners.
 */
public class TopLaner extends LolPlayer {
    /**
     * The summoner spell used by top laners.
     */
    public static final String SUMMONER = "teleport";

    private String mainChampion;

    /**
     * Constructs a TopLaner object with the specified summoner name, name, rank,
     * level, and main champion.
     *
     * @param summonerName the summoner name of the top laner
     * @param name         the name of the top laner
     * @param rank         the ranked rank of the top laner
     * @param level        the level of the top laner
     * @param mainChampion the main champion played by the top laner
     */
    public TopLaner(String summonerName, String name, RankedRank rank, int level, String mainChampion) {
        super(summonerName, name, Role.TOP, rank, level);
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the main champion played by the top laner.
     *
     * @return the main champion
     */
    public String getMainChampion() {
        return mainChampion;
    }

    /**
     * Sets the main champion played by the top laner.
     *
     * @param mainChampion the main champion to set
     */
    public void setMainChampion(String mainChampion) {
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the summoner spell used by top laners.
     *
     * @return the summoner spell
     */
    public String getSummoner() {
        return SUMMONER;
    }
}
