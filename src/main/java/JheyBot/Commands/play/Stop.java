package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.slashHandlers.Command;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
import JheyBot.musicHandler.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

@Command
public class Stop implements JSlashCommand {
   public static void stopMusic(GenericCommandInteractionEvent event){
      PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).schedule.endTrack();
      event.getGuild().getAudioManager().closeAudioConnection();

   }

   @Override
   public void callBack(SlashCommandInteractionEvent event) {
      if(!event.getMember().getVoiceState().inAudioChannel()){
         event.reply(event.getUser().getName() + " VOCE NEM TA EM UM CANAL KRL").queue();
         return;
      }
     event.getInteraction().getChannel().sendTyping().queue();
      stopMusic(event);
   }
   @Override
   public String getName() {
      return "stop";
   }

   @Override
   public String getDescription() {
      return "Stops currently music";
   }


}
