package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import JheyBot.Commands.play.musicHandler.MessageEmbeds;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.UserNotInVoiceChannelException;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


@CommandType(type = CommandTypes.BOTH)
public class Skip implements JSlashCommandInterface {

   public static void skipMusic(GenericCommandInteractionEvent event)throws UserNotInVoiceChannelException {
      if(!event.getMember().getVoiceState().inAudioChannel()) throw new UserNotInVoiceChannelException();
      PlayerManager.getInstance().getMusicManager(event.getGuild()).schedule.nextTrack();
   }
   @Override
   public void callBack(SlashCommandInteractionEvent event) {
      event.getInteraction().getChannel().sendTyping().queue();
      try{
      skipMusic(event);
      }catch (UserNotInVoiceChannelException e){
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Você não está em um canal")).queue();
      }
   }

   @Override
   public String getName() {
      return "skip";
   }

   @Override
   public String getDescription() {
      return "Skips the currently music";
   }

}