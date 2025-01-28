package bot.listenners.xp;

import org.jetbrains.annotations.NotNull;

import bot.modules.xp.XPModule;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class XpManagerListenner extends ListenerAdapter {
    private final XPModule XPModule;

    public XpManagerListenner(XPModule XPModule) {
        this.XPModule = XPModule;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (XPModule.canEarnXP(event.getAuthor().getId())) {
            XPModule.addXP(event.getAuthor().getId(), 10);
        }
    }
    
}
