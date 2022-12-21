package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.both.JBothHandlerInterface;
import JheyBot.Commands.CommandHandlers.both.JEventObject;
import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.Embeds.MessageEmbeds;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.UserNotInVoiceChannelException;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


@CommandType(type = CommandTypes.BOTH)
public class Skip implements JBothHandlerInterface {

   public static void skipMusic(JEventObject event)throws UserNotInVoiceChannelException {
      if(!event.getMember().getVoiceState().inAudioChannel()) throw new UserNotInVoiceChannelException();
      PlayerManager.getInstance().getMusicManager(event.getGuild()).schedule.nextTrack();
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
      //TODO if bot is not on channel reply error
      event.reply("a");
      event.getChannel().sendMessageEmbeds(MessageEmbeds.getGenericEmbed("SKIPINHO")).queue();
      try{
         skipMusic(event);
      }catch (UserNotInVoiceChannelException e){
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Você não está em um canal")).queue();
      }
   }

}