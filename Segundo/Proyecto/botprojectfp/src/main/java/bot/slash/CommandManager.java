package bot.slash;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import bot.slash.commands.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Clase que gestiona los comandos de barra Guarda un mapa con los comandos y su
 * nombre para poder ejecutarlos en el slashCmdListenner
 */
public class CommandManager implements EventListener {

    // Mapa con los comandos y su nombre
    private Map<String, ICommand> commands = new HashMap<>();

    /**
     * Método que añade un comando al mapa
     * 
     * @param String  name nombre del comando
     * @param Command command comando a añadir
     */
    public void addCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    /**
     * Método que ejecuta un comando
     * 
     * @param SlashCommandInteractionEvent event evento de interacción de comando de
     *                                     barra
     */
    public void execute(SlashCommandInteractionEvent event) {
        commands.get(event.getName()).handle(event);
    }

    /**
     * Método que devuelve el mapa de comandos
     * 
     * @return Map<String, Command> mapa de comandos
     */
    public Map<String, ICommand> getCommands() {
        return commands;
    }

    

}
