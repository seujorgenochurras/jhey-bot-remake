package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.Command;
import JheyBot.Commands.CommandHandlers.CommandTypes;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

@Command(type = CommandTypes.SLASH_COMMAND)
public class Stop implements JSlashCommandInterface {
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
