package logic.slash.commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.interactions.commands.Command.Option;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface ICommand {

    // attributes

    String name = "default";

    String description = "default";

    List<Option> options = new ArrayList<>();

    // methods

    void handle(SlashCommandInteraction event);

    // getters

    String getName();

    String getDescription();

    List<Option> getOptions();

    // setters

    void setName(String name);

    void setDescription(String description);

    void setOptions(List<Option> options);


}
