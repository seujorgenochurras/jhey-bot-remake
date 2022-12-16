package JheyBot.Commands.CommandHandlers.prefixHandlers;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class JPrefixCommand extends ListenerAdapter {

   public static List<JPrefixCommandInterface> commands = new ArrayList<>();

   public static void add(JPrefixCommandInterface command){
      commands.add(command);
   }


}
