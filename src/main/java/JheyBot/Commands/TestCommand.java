package JheyBot.Commands;

import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.CommandHandlers.prefixHandlers.JPrefixCommandInterface;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandType(type = CommandTypes.PREFIX_COMMAND)
public class TestCommand implements JPrefixCommandInterface {


   @Override
   public String getName() {
      return "teste";
   }

   @Override
   public void callBack(MessageReceivedEvent event) {
    event.getMessage().reply("iametenrocudasai").queue();
   }
}
