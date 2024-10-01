

import java.util.Scanner;

import listenners.ai_chat.GeminiClient;
import listenners.ai_chat.aiChat;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Arrays;

public class Main {

	/**
	 * Método principal
	 * @param String[] args argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		// Crea la instancia de Scanner
		Scanner scanner = new Scanner(System.in);

		// pide el token del bot por consola
		System.out.println("Introduce el token del bot:");
		String token = scanner.nextLine();

		// pide el API key de Gemini por consola
		System.out.println("Introduce el API key de Gemini:");
		String geminiKey = scanner.nextLine();

		// Crea la instancia de Gemini
		GeminiClient gemini = new GeminiClient(geminiKey);

		// Cierra el scanner
		scanner.close();

		// Crea la instancia JDA
		JDA jda = JDABuilder.create(
				// Añade los intents necesarios de una array[] to list
				Arrays.asList(GatewayIntent.values()))
				// Añade el token y construye la instancia
				.setToken(token).build();

		// Elimina todos los comandos
		jda.updateCommands().queue();

		// Añade los comandos
		jda.addEventListener(new aiChat(gemini));

	}

}
