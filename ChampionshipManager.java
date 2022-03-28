import java.io.IOException;

public interface ChampionshipManager {

    void displayMenu();
    void addnewDriver();
    void removeDriver();
    void viewStatistics();
    void changeDriver();
    void viewFormula1Table();
    void addacompletedRace();
    void saveData() throws IOException;
    void loadData() throws IOException, ClassNotFoundException;

}
