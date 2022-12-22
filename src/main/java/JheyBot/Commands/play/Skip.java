package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.both.JBothHandlerInterface;
import JheyBot.Commands.CommandHandlers.both.JEventObject;
import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.Embeds.MessageEmbeds;
import JheyBot.Commands.play.musicHandler.BotNotInVoiceChannelException;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.UserNotInVoiceChannelException;

@CommandType(type = CommandTypes.BOTH)
public class Skip implements JBothHandlerInterface {

   public static void skipMusic(JEventObject event) throws UserNotInVoiceChannelException, BotNotInVoiceChannelException {
      //If user is not on a voice channel
      UserNotInVoiceChannelException.getUserVoiceState(event.getMember());
      BotNotInVoiceChannelException.getOccur(event.getGuild());

      event.getChannel().sendMessageEmbeds(MessageEmbeds.getGenericEmbed("SKIPINHO")).queue();
      PlayerManager.getInstance().getMusicManager(event.getGuild()).schedule.nextTrack();
      --PlayerManager.getInstance().trackSize; //looks ugly

   }

   @Override
   public String getName() {
      return "skip";
   }

   @Override
   public String getDescription() {
      return "Skips the currently music";
   }

   @Override
   public void callBack(JEventObject event) {
      try{
         skipMusic(event);
      }catch (UserNotInVoiceChannelException e){
        event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Você não está em um canal")).queue();
      } catch (BotNotInVoiceChannelException e){
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed(e.getMessage())).queue();
      }

   }

}