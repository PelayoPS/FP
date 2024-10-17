package logic.slash.commands.moderation;

import java.util.List;
import java.util.ArrayList;

import logic.listenners.slash.SlashCmdListenner;
import logic.slash.commands.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;


public class UserInfo implements ICommand {

    // attributes

    private String name = "userinfo";

    private String description = "Get information about a user.";

    private List<OptionData> options = new ArrayList<>();

    /**
     * Constructor de la clase UserInfo
     */
    public UserInfo() {
        // añade la opción de usuario
        options.add(new OptionData(OptionType.USER, "user", "The user to get information about.").setRequired(false));
    }

    /**
     * Método que ejecuta el comando de userinfo
     * 
     * @param SlashCommandInteraction event evento de interacción de comando de
     *                                barra
     */
    @Override
    public void handle(SlashCommandInteraction event) {
        //!TODO: implementar
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
     * Método que devuelve la lista de opciones del comando
     * 
     * @return List<OptionData> lista de opciones del comando
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
     * Método que establece la lista de opciones del comando
     * 
     * @param List<OptionData> options lista de opciones del comando
     */
    @Override
    public void setOptions(List<OptionData> options) {
        this.options = options;
    }
}
