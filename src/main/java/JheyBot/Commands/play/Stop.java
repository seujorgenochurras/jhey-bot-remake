package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.both.JBothHandlerInterface;
import JheyBot.Commands.CommandHandlers.both.JEventObject;
import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.Embeds.MessageEmbeds;
import JheyBot.Commands.play.musicHandler.others.BotNotInVoiceChannelException;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.others.UserNotInVoiceChannelException;

@CommandType(type = CommandTypes.BOTH)
public class Stop implements JBothHandlerInterface {
   public static void stopMusic(JEventObject event)throws UserNotInVoiceChannelException, BotNotInVoiceChannelException {
      //If user is not on a voice channel
      UserNotInVoiceChannelException.getUserVoiceState(event.getMember());
      BotNotInVoiceChannelException.getOccur(event.getGuild());

      event.getChannel().sendMessageEmbeds(MessageEmbeds.getGenericEmbed("Sinal vermelho.. PAROOOU!!!")).queue();
      PlayerManager.getInstance().getMusicManager(event.getGuild()).schedule.endTrack();
      PlayerManager.getInstance().trackSize = 0;
      event.getGuild().getAudioManager().closeAudioConnection();
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
   public void callBack(JEventObject event) {

      try {
         stopMusic(event);
      }catch (UserNotInVoiceChannelException e){
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Você não está em um canal")).queue();
      } catch (BotNotInVoiceChannelException e){
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed(e.getMessage())).queue();
      }
   }

   @Override
   public String[] getNames() {
      return new String[]{"d", "die", "sto", "DIE", "D", "STO"};
   }
}
