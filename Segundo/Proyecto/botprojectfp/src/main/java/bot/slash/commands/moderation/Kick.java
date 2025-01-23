package bot.slash.commands.moderation;

import java.util.ArrayList;
import java.util.List;

import bot.slash.commands.ICommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Kick implements ICommand {

    // attributes

    private String name = "kick";

    private String description = "Kicks a user from the server it was executed on.";

    private List<OptionData> options = new ArrayList<>();

    /**
     * Constructor de la clase Kick
     */
    public Kick() {
        // añade la opción de usuario
        options.add(new OptionData(OptionType.USER, "user", "The user to kick.").setRequired(true));
    }

    /**
     * Método que ejecuta el comando de kick
     * 
     * @param SlashCommandInteraction event evento de interacción de comando de
     *                                barra
     */
    @Override
    public void handle(SlashCommandInteraction event) {
        // checks if the user has the permission to kick
        if (event.getMember().hasPermission(net.dv8tion.jda.api.Permission.KICK_MEMBERS)) {
            // bans the user and if it's successful sends a message to the channel
            if (event.getOption("user") != null) {
                event.getGuild().kick(event.getOption("user").getAsUser()).queue();

                event.reply("User kicked.").queue();
            } else {
                event.reply("You need to specify a user.").queue();
            }
        } else {
            // sends a message to the channel
            event.reply("You don't have the permission to kick.").queue();
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
     * @return List<OptionData> opciones del comando
     */
    @Override
    public List<OptionData> getOptions() {
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
     * @param List<OptionData> options opciones del comando
     */
    @Override
    public void setOptions(List<OptionData> options) {
        this.options = options;
    }

}
