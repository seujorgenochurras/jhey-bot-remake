package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


@CommandType(type = CommandTypes.SLASH_COMMAND)
public class Skip implements JSlashCommandInterface {

   public static void skipMusic(GenericCommandInteractionEvent event) {
      PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).schedule.nextTrack();
   }
   @Override
   public void callBack(SlashCommandInteractionEvent event) {
      event.getInteraction().getChannel().sendTyping().queue();
      skipMusic(event);
   }

   @Override
   public String getName() {
      return "skip";
   }

   @Override
   public String getDescription() {
      return "Skips the currently music";
   }

}