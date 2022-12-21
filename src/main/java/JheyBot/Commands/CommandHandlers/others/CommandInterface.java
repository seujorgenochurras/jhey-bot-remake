package JheyBot.Commands.CommandHandlers.others;

public interface CommandInterface{
   String getName();

   default String[] getNames(){
      return null;
   }
   default void build(){}

}
