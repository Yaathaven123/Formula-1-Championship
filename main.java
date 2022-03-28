import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);
        String input;

        Formula1ChampionshipManager f1manager = new Formula1ChampionshipManager();



        try{                                        // load previous file if available
            f1manager.loadData();
        }catch (FileNotFoundException e){
            System.out.println("No file found");

        }


        do{
            f1manager.displayMenu();
            input = s.next();
            switch (input){
                case("1"):
                    f1manager.addnewDriver();
                    break;
                case("2"):
                    f1manager.removeDriver();
                    break;
                case("3"):
                    f1manager.changeDriver();
                    break;
                case("4"): //
                    f1manager.viewStatistics();
                    break;
                case("5"):
                    f1manager.viewFormula1Table();
                    break;
                case("6"):
                    f1manager.addacompletedRace();
                    break;
                case("7"):
                    f1manager.saveData();
                    break;
                case("8"):
                    new gui();
                    break;

            }
        }while(!input.equals("9"));


    }
}
