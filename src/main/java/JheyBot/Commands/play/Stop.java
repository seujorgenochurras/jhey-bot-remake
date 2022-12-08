package JheyBot.Commands.play;

import JheyBot.musicHandler.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Stop extends ListenerAdapter {
   public static void stopMusic(GenericCommandInteractionEvent event){
      PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).schedule.endTrack();
      event.getGuild().getAudioManager().closeAudioConnection();

   }
}
