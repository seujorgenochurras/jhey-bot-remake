package JheyBot.CommandHandlers.prefixHandlers;

<<<<<<<< HEAD:src/main/java/JheyBot/CommandHandlers/prefixHandlers/JPrefixCommandInterface.java
import JheyBot.CommandHandlers.others.CommandInterface;
========
import JheyBot.Commands.CommandHandlers.others.ICommand;
>>>>>>>> c1e693c81548613af02dd0a0a69375f39eee92ef:src/main/java/JheyBot/CommandHandlers/prefixHandlers/IPrefixCommand.java
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
