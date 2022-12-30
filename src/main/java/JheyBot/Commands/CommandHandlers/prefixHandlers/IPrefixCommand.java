package JheyBot.Commands.CommandHandlers.prefixHandlers;

import JheyBot.Commands.CommandHandlers.others.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public interface IPrefixCommand extends ICommand {

   void callBack(MessageReceivedEvent event);

   default List<String> getArgs(MessageReceivedEvent event){
      List<String> args = new ArrayList<>(List.of(event.getMessage().getContentRaw().split(" ")));

    if(args.size() < 2) throw new NullPointerException();

    args.remove(0);
    return args;
   }

   @Override
   default void build(){
      JPrefixCommand.add(this);
   }


}
