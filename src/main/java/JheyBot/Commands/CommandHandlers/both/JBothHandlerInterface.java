package JheyBot.Commands.CommandHandlers.both;

import JheyBot.Commands.CommandHandlers.others.CommandInterface;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public interface JBothHandlerInterface extends CommandInterface {
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
