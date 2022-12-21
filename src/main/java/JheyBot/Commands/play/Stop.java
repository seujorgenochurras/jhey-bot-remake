package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.both.JBothHandlerInterface;
import JheyBot.Commands.CommandHandlers.both.JEventObject;
import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import JheyBot.Commands.Embeds.MessageEmbeds;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.UserNotInVoiceChannelException;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

@CommandType(type = CommandTypes.BOTH)
public class Stop implements JBothHandlerInterface {
   public static void stopMusic(JEventObject event)throws UserNotInVoiceChannelException {
      if(!event.getMember().getVoiceState().inAudioChannel()) throw new UserNotInVoiceChannelException();

      PlayerManager.getInstance().getMusicManager(event.getGuild()).schedule.endTrack();
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
      //TODO if bot is not on channel reply error
      event.reply("a");
      event.getChannel().sendMessageEmbeds(MessageEmbeds.getGenericEmbed("Sinal vermelho.. PAROOOU!!!")).queue();
      try {
         stopMusic(event);
      }catch (UserNotInVoiceChannelException e){
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Você não está em um canal")).queue();
      }
   }


}
