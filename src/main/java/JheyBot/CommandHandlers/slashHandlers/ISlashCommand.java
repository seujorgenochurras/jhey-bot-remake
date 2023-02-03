package JheyBot.CommandHandlers.slashHandlers;

<<<<<<<< HEAD:src/main/java/JheyBot/CommandHandlers/slashHandlers/JSlashCommandInterface.java
import JheyBot.CommandHandlers.others.CommandInterface;
========
import JheyBot.Commands.CommandHandlers.others.ICommand;
>>>>>>>> c1e693c81548613af02dd0a0a69375f39eee92ef:src/main/java/JheyBot/CommandHandlers/slashHandlers/ISlashCommand.java
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
