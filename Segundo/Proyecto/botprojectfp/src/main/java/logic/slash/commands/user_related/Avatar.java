package logic.slash.commands.user_related;

import java.util.ArrayList;
import java.util.List;

import logic.slash.commands.ICommand;
import net.dv8tion.jda.api.interactions.commands.Command.Option;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Avatar implements ICommand {

    // attributes

    private String name = "avatar";

    private String description = "Shows the avatar of the user it was executed on.";

    private List<Option> options = new ArrayList<>() {
        {
            new OptionData(OptionType.USER, "user", "The user to get the avatar from, if no user proveded shows self avatar.").setRequired(false);
        }
    };

    /**
     * Método que ejecuta el comando de avatar
     * 
     * @param SlashCommandInteraction event evento de interacción de comando de
     *                                barra
     */
    @Override
    public void handle(SlashCommandInteraction event) {
        event.reply(event.getUser().getEffectiveAvatarUrl() + "?size=1920").queue();
        // comprobar si se ha proporcionado un usuario
        if (event.getOption("user") != null) {
            // enviar el avatar del usuario en 1920x1080 y en formato png
            event.reply(event.getOption("user").getAsUser().getEffectiveAvatarUrl() + "?size=1920").queue();
        } else {
            // enviar el avatar del usuario que ejecutó el comando en 1920x1080 y en formato png
            event.reply(event.getUser().getEffectiveAvatarUrl() + "?size=1920").queue();
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
