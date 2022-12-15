package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.slashHandlers.CommandAnnotation;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
import JheyBot.musicHandler.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

@CommandAnnotation
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
      return "null";
   }

   @Override
   public List<OptionData> getOptions() {
      return null;
   }





}