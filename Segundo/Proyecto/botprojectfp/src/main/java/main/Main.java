package main;

import java.util.Scanner;

import logic.listenners.ai_chat.GeminiClient;
import logic.listenners.ai_chat.aiChat;
import logic.listenners.slash.SlashCmdListenner;
import logic.slash.CommandManager;
import logic.slash.commands.ICommand;
import logic.slash.commands.moderation.Ban;
import logic.slash.commands.moderation.InviteInfo;
import logic.slash.commands.moderation.Kick;
import logic.slash.commands.owner_only.OnwerSendAiText;
import logic.slash.commands.user_related.Avatar;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	/**
	 * Método principal
	 * 
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
		// jda.updateCommands().queue();

		// Añade los comandos usando la clase CommandManager
		CommandManager manager = new CommandManager();
		addCommands(manager, gemini);

		// El manager es un listenner que llama al execute correspondiente según el
		// comando
		jda.addEventListener(new aiChat(gemini), new SlashCmdListenner(manager));

		// Añade los comandos a la lista de comandos de barra
		jda.updateCommands().addCommands(getSlashCommandList(manager)).queue();
		jda.getGuilds().forEach(guild -> {
			guild.updateCommands().addCommands(getSlashCommandList(manager)).queue();
		});

		// Muestra los comandos actualizados
		showUpdatedCommands(jda);

	}

	/**
	 * Método que muestra los comandos actualizados
	 * 
	 * @param JDA jda instancia de JDA
	 */
	private static void showUpdatedCommands(JDA jda) {
		jda.retrieveCommands().queue(commands -> {
			System.out.println("Loaded commands:");
			commands.forEach(command -> {
				// shows the command name, description and options with the logger format of jda
				System.out.println("Command: " + command.getName() + " - " + command.getDescription() + " - Options: "
						+ command.getOptions());
			});
		});

	}

	/**
	 * Método que devuelve un array de OptionData a partir de una lista de opciones
	 * 
	 * @param List<OptionData> options lista de opciones
	 * @return OptionData[] array de OptionData
	 */
	private static OptionData[] getOptionData(List<OptionData> options) {
		OptionData[] optionData = new OptionData[options.size()];
		for (int i = 0; i < options.size(); i++) {
			optionData[i] = new OptionData(options.get(i).getType(), options.get(i).getName(),
					options.get(i).getDescription());
		}
		return optionData;
	}

	/**
	 * Método que devuelve una lista de comandos de barra
	 * 
	 * @param manager manager de comandos
	 * @return List<SlashCommandData> lista de comandos de barras
	 */
	private static List<SlashCommandData> getSlashCommandList(CommandManager manager) {
		List<SlashCommandData> slashCommandList = new ArrayList<>();
		manager.getCommands().forEach((name, command) -> {
			slashCommandList.add(
					Commands.slash(name, command.getDescription()).addOptions(getOptionData(command.getOptions())));
		});
		return slashCommandList;
	}

	/**
	 * Método que añade los comandos al manager
	 * 
	 * @param CommandManager manager manager de comandos
	 */
	private static void addCommands(CommandManager manager, GeminiClient gemini) {
		// owner only commands
		ICommand ownerSendAiText = new OnwerSendAiText(gemini);
		manager.addCommand(ownerSendAiText.getName(), ownerSendAiText);
		
		// mod commands
		ICommand ban = new Ban();
		manager.addCommand(ban.getName(), ban);
		ICommand kick = new Kick();
		manager.addCommand(kick.getName(), kick);
		ICommand inviteInfo = new InviteInfo();
		manager.addCommand(inviteInfo.getName(), inviteInfo);

		// user related commands
		ICommand avatar = new Avatar();
		manager.addCommand(avatar.getName(), avatar);
	}

}
