package JheyBot.Commands.CommandHandlers.others;

public interface ICommand {
   String getName();

   default String[] getNames(){
      return null;
   }
   default void build(){}

}
