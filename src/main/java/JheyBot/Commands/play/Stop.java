package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.UserNotInVoiceChannelException;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

@CommandType(type = CommandTypes.SLASH_COMMAND)
public class Stop implements JSlashCommandInterface {
   public static void stopMusic(GenericCommandInteractionEvent event)throws UserNotInVoiceChannelException {
      if(!event.getMember().getVoiceState().inAudioChannel()) throw new UserNotInVoiceChannelException();

      PlayerManager.getInstance().getMusicManager(event.getGuild()).schedule.endTrack();
      event.getGuild().getAudioManager().closeAudioConnection();

   }

   @Override
   public void callBack(SlashCommandInteractionEvent event) {
     event.getInteraction().getChannel().sendTyping().queue();
      try {
      stopMusic(event);
      }catch (UserNotInVoiceChannelException e){
         event.reply("VOCE NEM TA EM UM CANAL KRL").queue();
      }
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
