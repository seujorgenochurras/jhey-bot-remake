package JheyBot.Commands.CommandHandlers;

import JheyBot.Commands.CommandHandlers.slashHandlers.JheySlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface CommandInterface {

   void callBack(SlashCommandInteractionEvent event);
   String getName();

   String getDescription();

//   default void build() {
//      JheySlashCommand.add(this);
//   }
}
