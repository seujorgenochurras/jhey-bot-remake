package JheyBot.Commands.CommandHandlers.slashHandlers;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.annotation.Nullable;
import java.util.List;

public interface JSlashCommand {

   void callBack(SlashCommandInteractionEvent event);
   String getName();

   String getDescription();

   @Nullable default List<OptionData> getOptions() {return null;}

   default void build() {
    JheySlashCommand2.add(this);
   }
}
