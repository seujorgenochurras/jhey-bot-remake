package JheyBot.CommandHandlers.both;

<<<<<<<< HEAD:src/main/java/JheyBot/CommandHandlers/both/JBothHandlerInterface.java
import JheyBot.CommandHandlers.others.CommandInterface;
========
import JheyBot.Commands.CommandHandlers.others.ICommand;
>>>>>>>> c1e693c81548613af02dd0a0a69375f39eee92ef:src/main/java/JheyBot/CommandHandlers/both/IBothICommand.java
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public interface IBothICommand extends ICommand {
   String getDescription();

   default List<String> getArgs(JEventObject event){
      List<String> args = new ArrayList<>(List.of(event.getMessage().getContentRaw().split(" ")));

      if(args.size() < 2) throw new NullPointerException();

      args.remove(0);
      return args;
   }


   @Nullable default List<OptionData> getOptions() {return null;}

   @Override
   default void build(){
      JBothHandler.addCommand(this);
   }

   void callBack(JEventObject event);
}
