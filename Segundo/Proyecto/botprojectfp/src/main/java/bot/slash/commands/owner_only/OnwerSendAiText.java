package bot.slash.commands.owner_only;

import java.util.ArrayList;
import java.util.List;

import bot.listenners.ai_chat.GeminiClient;
import bot.slash.commands.ICommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class OnwerSendAiText implements ICommand {

    // attributes

    private String name = "aichat";

    private String description = "Ai chat with owner's acc, only owner can use this command.";

    private List<OptionData> options = new ArrayList<>();

    private GeminiClient client = null;

    // constructor

    /**
     * Constructor de la clase Warn
     */
    public OnwerSendAiText(GeminiClient client) {

        this.client = client;

        OptionData prompt = new OptionData(OptionType.STRING, "prompt", "Promtp to send to the ai.").setRequired(true);

        options.add(prompt);
    }

    // methods

    /**
     * Método que ejecuta el comando de warn Warns a user
     * 
     * @param SlashCommandInteraction event evento de interacción de comando de
     *                                barra
     */
    @Override
    public void handle(SlashCommandInteraction event) {
        // checks if the user is the owner of the bot
        if (!event.getUser().getId().equals("575724048097476628")) {
            event.deferReply(true).queue();
            event.getChannel().sendMessage("You are not the owner of the bot.").queue();
            return;
        }
        event.deferReply(true).queue();
        try {
            GeminiClient client = this.client;
            try {
                // Generar la respuesta
                String answer = client.generateContent(event.getOption("prompt").getAsString());
                Robot robot = new Robot();

                String myString = answer;

                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection stringSelection = new StringSelection(myString);
                clipboard.setContents(stringSelection, null);
                // robot paste from clipboard
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);

                // robot press enter
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (Exception e) {
                // Enviar el mensaje de error
                // !TODO: crear un sistema de logs
                
                event.getChannel().sendMessage(e.getMessage()).queue();
                e.printStackTrace();
            }
            // ends defer reply
            event.getHook().sendMessage("Message sent to the AI.").queue();

        } catch (Exception e) {
            e.printStackTrace();
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
     * @param name nombre del comando
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método que establece la descripción del comando
     * 
     * @param description descripción del comando
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Método que establece las opciones del comando
     * 
     * @param options opciones del comando
     */
    @Override
    public void setOptions(List<OptionData> options) {
        this.options = options;
    }

}
