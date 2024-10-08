package logic.listenners.slash;

import org.jetbrains.annotations.NonNls;

import logic.slash.CommandManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;



public class SlashCmdListenner extends ListenerAdapter {

    private CommandManager manager;

    /**
     * Constructor de la clase
     * 
     * @param CommandManager manager manager de comandos
     */
    public SlashCmdListenner(CommandManager manager) {
        this.manager = manager;
    }

    /**
     * Método que se ejecuta cuando se recibe una interacción de un comando de barra
     * 
     * @param SlashCommandInteractionEvent evento de interacción de comando de barra
     */
    @Override
    public void onSlashCommandInteraction(@NonNls SlashCommandInteractionEvent event) {
        manager.execute(event);
    }
}
