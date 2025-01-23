package bot.listenners.ai_chat;

import org.jetbrains.annotations.NonNls;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class aiChat extends ListenerAdapter {

    // Cliente de Gemini inicializado a null
    private GeminiClient client = null;

    /**
     * Constructor que recibe el cliente de Gemini
     * 
     * @param GeminiClient cliente de Gemini
     */
    public aiChat(GeminiClient client) {
        this.client = client;
    }

    /**
     * Método que se ejecuta cuando se recibe un mensaje
     * 
     * @param MessageReceivedEvent event con la información del mensaje
     */
    @Override
    public void onMessageReceived(@NonNls MessageReceivedEvent event) {

        /*
         * !TODO: crear un sistema de logs
         * para por si fuera necesario guardar los prompts y las respuestas
         */

        // Si el mensaje no es de un bot
        if (!event.getAuthor().isBot()) {
            // Si el mensaje menciona al bot
            if (event.getMessage().getContentRaw().contains(event.getJDA().getSelfUser().getAsMention())
                    || event.getMessage().getReferencedMessage() != null) {
                // Responder con la respuesta de openAI

                try {
                    // Generar la respuesta
                    String answer = client.generateContent(event.getMessage().getContentRaw()
                            .replace(event.getJDA().getSelfUser().getAsMention(), ""));

                    if (answer.length() >= 2000) {
                        // bucle para enviar el mensaje en trozos de 2000 caracteres
                        for (int i = 0; i < answer.length(); i += 1999) {
                            // obtener el trozo
                            String chunk = answer.substring(i, Math.min(answer.length(), i + 1999));
                            // enviar el trozo
                            event.getChannel().sendMessage(chunk).queue();
                        }

                    } else {
                        // enviar el mensaje
                        event.getChannel().sendMessage(answer).queue();
                    }
                } catch (Exception e) {
                    // Enviar el mensaje de error
                    // !TODO: crear un sistema de logs
                    event.getChannel().sendMessage(e.getMessage()).queue();
                }
            }
        }

    }

}
