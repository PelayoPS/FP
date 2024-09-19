package model;

/**
 * The ADC class represents an ADC (Attack Damage Carry) player in the game
 * League of Legends.
 * It extends the LolPlayer class and adds additional properties and methods
 * specific to ADC players.
 */
public class ADC extends LolPlayer {
    /**
     * The summoner spell used by all ADC players.
     */
    public static final String SUMMONER = "heal";

    private String mainChampion;

    /**
     * Constructs a new ADC player with the specified summoner name, name, rank,
     * level, and main champion.
     *
     * @param summonerName the summoner name of the ADC player
     * @param name         the name of the ADC player
     * @param rank         the ranked rank of the ADC player
     * @param level        the level of the ADC player
     * @param mainChampion the main champion played by the ADC player
     */
    public ADC(String summonerName, String name, RankedRank rank, int level, String mainChampion) {
        super(summonerName, name, Role.ADC, rank, level);
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the main champion played by the ADC player.
     *
     * @return the main champion played by the ADC player
     */
    public String getMainChampion() {
        return mainChampion;
    }

    /**
     * Sets the main champion played by the ADC player.
     *
     * @param mainChampion the main champion to be set
     */
    public void setMainChampion(String mainChampion) {
        this.mainChampion = mainChampion;
    }

    /**
     * Returns the summoner spell used by all ADC players.
     *
     * @return the summoner spell used by all ADC players
     */
    public String getSummoner() {
        return SUMMONER;
    }
}
