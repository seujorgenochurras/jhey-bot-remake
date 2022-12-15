package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.slashHandlers.Command;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
import JheyBot.musicHandler.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


@Command
public class Skip implements JSlashCommand {

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