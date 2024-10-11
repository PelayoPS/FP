package logic.slash.commands.moderation;

import java.util.List;
import java.util.ArrayList;

import logic.slash.commands.ICommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
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
    
}
