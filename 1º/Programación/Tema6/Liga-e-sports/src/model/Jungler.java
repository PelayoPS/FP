package model;

/**
 * The Jungler class represents a player who specializes in the jungle role in
 * the game League of Legends.
 * It extends the LolPlayer class and adds additional properties and methods
 * specific to junglers.
 */
public class Jungler extends LolPlayer {
    /**
     * The summoner spell used by junglers.
     */
    public static final String SUMMONER = "smite";

    private String mainChampion;

    /**
     * Constructs a new Jungler object with the specified summoner name, name, rank,
     * level, and main champion.
     *
     * @param summonerName the summoner name of the jungler
     * @param name         the name of the jungler
     * @param rank         the ranked rank of the jungler
     * @param level        the level of the jungler
     * @param mainChampion the main champion played by the jungler
     */
    public Jungler(String summonerName, String name, RankedRank rank, int level, String mainChampion) {
        super(summonerName, name, Role.JUNGLE, rank, level);
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the main champion played by the jungler.
     *
     * @return the main champion
     */
    public String getMainChampion() {
        return mainChampion;
    }

    /**
     * Sets the main champion played by the jungler.
     *
     * @param mainChampion the main champion to set
     */
    public void setMainChampion(String mainChampion) {
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the summoner spell used by junglers.
     *
     * @return the summoner spell
     */
    public String getSummoner() {
        return SUMMONER;
    }
}