package logic.slash.commands.moderation;

import java.util.ArrayList;
import java.util.List;

import logic.slash.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class InviteInfo implements ICommand {

    // attributes

    private String name = "inviteinfo";

    private String description = "Shows the invite info of the server it was executed on.";

    private List<OptionData> options = new ArrayList<>();

    // constructor

    /**
     * Constructor de la clase InviteInfo
     */
    public InviteInfo() {
        options.add(new OptionData(net.dv8tion.jda.api.interactions.commands.OptionType.USER, "user",
                "The user to get the invite info from.").setRequired(true));

    }

    // methods

    /**
     * Método que ejecuta el comando de inviteinfo Only execute if its used in a
     * guild Shows an embed message that shows who invited the user to the server
     * when the user joined the server and the invite code also checks if the user
     * is an alt account
     * 
     * @param SlashCommandInteraction event evento de interacción de comando de
     *                                barra
     */
    @Override
    public void handle(SlashCommandInteraction event) {
        // checks if the event is from a guild
        if (event.getGuild() != null) {
            // checks if the user has the permission to see the invite info
            if (event.getMember().hasPermission(net.dv8tion.jda.api.Permission.MODERATE_MEMBERS)) {
                // checks if the user has been invited
                if (event.getMember().getTimeJoined().toEpochSecond() == event.getMember().getUser().getTimeCreated()
                        .toEpochSecond()) {
                    // sends a message to the channel
                    event.reply("This user was not invited.").queue();
                } else {
                    // gets the invite code
                    String inviteCode = event.getGuild().retrieveInvites().complete().stream()
                            .filter(invite -> invite.getInviter().getId().equals(event.getMember().getId())).findFirst()
                            .get().getCode();
                    // gets the inviter
                    String inviter = event.getGuild().retrieveInvites().complete().stream()
                            .filter(invite -> invite.getInviter().getId().equals(event.getMember().getId())).findFirst()
                            .get().getInviter().getAsTag();
                    // gets the join date of the user provided in that guild
                    String joinDate = event.getOption("user").getAsMember().getTimeJoined().toString();
                    // gets the user avatar
                    String userAvatar = event.getOption("user").getAsUser().getEffectiveAvatarUrl();
                    // gets the user name
                    String userName = event.getOption("user").getAsUser().getName();
                    // gets the user tag
                    String userTag = event.getOption("user").getAsUser().getAsTag();
                    // sends an embed message to the channel
                    event.replyEmbeds(buildEmbed(inviter, inviteCode, joinDate, userAvatar, userName, userTag)).queue();
                }
            } else {
                // sends a message to the channel
                event.reply("You don't have the permission to see the invite info.").queue();
            }
        } else {
            // sends a message to the channel
            event.reply("This command can only be used in a guild.").queue();
        }

    }

    /**
     * Método que construye el mensaje embebido Donde se muestra quien invitó al
     * usuario al servidor cuando el usuario se unió al servidor, el código de
     * invitación foto de perfil del usuario, nombre de usuario y tag
     * 
     * @return
     */
    private MessageEmbed buildEmbed(String inviter, String inviteCode, String joinDate, String userAvatar,
            String userName, String userTag) {
        // format date to yyyy-MM-dd HH:mm:ss
        joinDate = joinDate.replace("T", " ").replace("Z", "");
        MessageEmbed embed = new EmbedBuilder().setTitle("Invite Info").addField("Invited by", inviter, false)
                .addField("Invite code", inviteCode, false).addField("Join date", joinDate, false)
                .setThumbnail(userAvatar).setAuthor(userName, null, userAvatar).setFooter(userTag).build();
        return embed;
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
     * @param String name nombre del comando
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método que establece la descripción del comando
     * 
     * @param String description descripción del comando
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Método que establece las opciones del comando
     * 
     * @param List<OptionData> options opciones del comando
     */
    @Override
    public void setOptions(List<OptionData> options) {
        this.options = options;
    }

}
