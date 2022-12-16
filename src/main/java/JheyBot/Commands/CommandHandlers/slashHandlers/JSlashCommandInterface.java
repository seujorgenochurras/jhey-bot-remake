package JheyBot.Commands.CommandHandlers.slashHandlers;

import JheyBot.Commands.CommandHandlers.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.annotation.Nullable;
import java.util.List;

public interface JSlashCommandInterface extends CommandInterface {

   @Override
   void callBack(SlashCommandInteractionEvent event);

   @Nullable default List<OptionData> getOptions() {return null;}

   default void build() {
    JheySlashCommand.add(this);
   }
}
