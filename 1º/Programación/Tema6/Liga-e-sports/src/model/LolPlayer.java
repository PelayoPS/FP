package model;

import java.util.Objects;

/**
 * Represents a League of Legends player.
 * This class contains information about the player's summoner name, name, role,
 * rank, and level.
 */
public class LolPlayer {
    String summonerName;
    String name;
    Role role;
    RankedRank rank;
    int level;

    /**
     * Constructs a LolPlayer object with the specified summoner name, name, role,
     * rank, and level.
     * 
     * @param summonerName the summoner name of the player
     * @param name         the name of the player
     * @param role         the role of the player
     * @param rank         the ranked rank of the player
     * @param level        the level of the player
     */
    public LolPlayer(String summonerName, String name, Role role, RankedRank rank, int level) {
        this.summonerName = summonerName;
        this.name = name;
        this.role = role;
        this.rank = rank;
        this.level = level;
    }

    /**
     * Returns the summoner name of the player.
     * 
     * @return the summoner name of the player
     */
    public String getSummonerName() {
        return summonerName;
    }

    /**
     * Returns the name of the player.
     * 
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the role of the player.
     * 
     * @return the role of the player
     */
    public Role getRole() {
        return role;
    }

    /**
     * Returns the ranked rank of the player.
     * 
     * @return the ranked rank of the player
     */
    public RankedRank getRank() {
        return rank;
    }

    /**
     * Returns the level of the player.
     * 
     * @return the level of the player
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the summoner name of the player.
     * 
     * @param summonerName the summoner name to set
     */
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    /**
     * Sets the name of the player.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the role of the player.
     * 
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Sets the ranked rank of the player.
     * 
     * @param rank the ranked rank to set
     */
    public void setRank(RankedRank rank) {
        this.rank = rank;
    }

    /**
     * Sets the level of the player.
     * 
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Returns a string representation of the LolPlayer object.
     * 
     * @return a string representation of the LolPlayer object
     */
    @Override
    public String toString() {
        return "LolPlayer [summonerName=" + summonerName + ", name=" + name + ", role=" + role + ", rank=" + rank
                + ", level=" + level + "]";
    }

    /**
     * Returns the hash code value for the LolPlayer object.
     * 
     * @return the hash code value for the LolPlayer object
     */
    @Override
    public int hashCode() {
        return Objects.hash(summonerName);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LolPlayer other = (LolPlayer) obj;
        return Objects.equals(summonerName, other.summonerName);
    }
}