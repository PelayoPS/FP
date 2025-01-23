package bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class BotManager {
    private JDA jda;

    public void startBot(String token) throws Exception {
        jda = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("Managing Modules"))
                .build();
        jda.awaitReady();
        System.out.println("Bot is ready!");
    }

    public void stopBot() {
        if (jda != null) {
            jda.shutdown();
            System.out.println("Bot stopped.");
        }
    }
}