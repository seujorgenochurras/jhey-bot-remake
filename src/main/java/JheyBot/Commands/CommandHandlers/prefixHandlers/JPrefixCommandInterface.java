package JheyBot.Commands.CommandHandlers.prefixHandlers;

import JheyBot.Commands.CommandHandlers.others.CommandInterface;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface JPrefixCommandInterface extends CommandInterface {

   void callBack(MessageReceivedEvent event);

   @Override
   default void build(){
      JPrefixCommand.add(this);
   }


}
