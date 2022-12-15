package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.slashHandlers.CommandAnnotation;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
import JheyBot.musicHandler.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

@CommandAnnotation
public class Stop implements JSlashCommand {
   public static void stopMusic(GenericCommandInteractionEvent event){
      PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).schedule.endTrack();
      event.getGuild().getAudioManager().closeAudioConnection();

   }

   @Override
   public void callBack(SlashCommandInteractionEvent event) {
      System.out.println("awiohdioahwdaoiwhawoidzxncmznxbnfhjkgkjfghkjfgh");
   }
   @Override
   public String getName() {
      return "stop";
   }

   @Override
   public String getDescription() {
      return "Stops currently music";
   }

   @Override
   public List<OptionData> getOptions() {
      return null;
   }
}
