package logic.slash.commands.moderation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import logic.slash.commands.ICommand;
import net.dv8tion.jda.api.interactions.commands.Command.Option;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public class Ban implements ICommand {

    // attributes

    private String name = "ban";

    private String description = "Bans a user from the server it was executed on.";

    private List<Option> options = new ArrayList<>() {
        {
            new OptionData(OptionType.USER, "user", "The user to ban.").setRequired(true);
        }
    };

    /**
     * Método que ejecuta el comando de ban
     * 
     * @param SlashCommandInteraction event evento de interacción de comando de
     *                                barra
     */
    @Override
    public void handle(SlashCommandInteraction event) {
        // checks if the user has the permission to ban
        if (event.getMember().hasPermission(net.dv8tion.jda.api.Permission.BAN_MEMBERS)) {
            // bans the user and if it's successful sends a message to the channel
            if (event.getOption("user") != null) {
                event.getGuild().ban(event.getOption("user").getAsUser(), 7, TimeUnit.DAYS).queue();

                event.reply("User banned.").queue();
            } else {
                event.reply("You need to specify a user.").queue();
            }
        } else {
            // sends a message to the channel
            event.reply("You don't have the permission to ban.").queue();
        }
    }

    // getters

    /**
     * Método que devuelve el nombre del comando
     * 
     * @return String nombre del comando
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Método que devuelve la descripción del comando
     * 
     * @return String descripción del comando
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Método que devuelve las opciones del comando
     * 
     * @return List<Option> opciones del comando
     */
    @Override
    public List<Option> getOptions() {
        return options;
    }

    // setters

    /**
     * Método que establece el nombre del comando
     * 
     * @param String name nombre del comando
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método que establece la descripción del comando
     * 
     * @param String description descripción del comando
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Método que establece las opciones del comando
     * 
     * @param List<Option> options opciones del comando
     */
    @Override
    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
