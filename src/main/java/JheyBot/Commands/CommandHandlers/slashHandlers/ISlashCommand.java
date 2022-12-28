package JheyBot.Commands.CommandHandlers.slashHandlers;

import JheyBot.Commands.CommandHandlers.others.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.annotation.Nullable;
import java.util.List;

public interface ISlashCommand extends ICommand {

   void callBack(SlashCommandInteractionEvent event);

   String getDescription();

   @Nullable default List<OptionData> getOptions() {return null;}

   default void build() {
    JSlashCommand.add(this);
   }
}
