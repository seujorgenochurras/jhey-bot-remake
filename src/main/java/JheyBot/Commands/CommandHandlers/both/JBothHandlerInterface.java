package JheyBot.Commands.CommandHandlers.both;

import JheyBot.Commands.CommandHandlers.prefixHandlers.JPrefixCommandInterface;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;

public interface JBothHandlerInterface extends JPrefixCommandInterface, JSlashCommandInterface {
   String getDescription();

   @Override
   default void build(){
      JBothHandler.add(this);
   }

}
