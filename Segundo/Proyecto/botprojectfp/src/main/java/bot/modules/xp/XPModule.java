package bot.modules.xp;

import java.util.HashMap;

public class XPModule {
    private final HashMap<String, Long> lastMessageTime = new HashMap<>();
    private final int cooldownSeconds = 60;

    public boolean canEarnXP(String userId) {
        long now = System.currentTimeMillis();
        if (!lastMessageTime.containsKey(userId) || 
            now - lastMessageTime.get(userId) > cooldownSeconds * 1000) {
            lastMessageTime.put(userId, now);
            return true;
        }
        return false;
    }
}
