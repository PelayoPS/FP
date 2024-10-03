package logic.slash.commands;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface ICommand extends Command {

    // attributes

    String name = null;

    String description = null;

    // methods

    void handle(SlashCommandInteraction event);

    // getters

    String getName();

    String getDescription();

    // setters

    void setName(String name);

    void setDescription(String description);

}
