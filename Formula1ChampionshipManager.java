import java.io.*;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager   {


    protected static ArrayList<Formula1Driver> formula1drivers = new ArrayList<Formula1Driver>();
    protected static ArrayList<Race> races = new ArrayList<>();
    protected static ArrayList<Formula1Driver> pointsSort;  // stores drivers ordered by points




    private Scanner s = new Scanner(System.in);
    private String input;



    @Override
    public void displayMenu() {

        System.out.println("\n-Select an option-\n");
        System.out.println("1.Enter new driver");
        System.out.println("2.Remove existing driver");
        System.out.println("3.Change driver in team");
        System.out.println("4.View statistics");
        System.out.println("5.View F1 table");
        System.out.println("6.Add a completed race");
        System.out.println("7.Save data to file");
        System.out.println("8.Graphical User Interface");
        System.out.println("9.Exit Program");

    }

    @Override
    public void addnewDriver(){                                                              //Create a new driver
        System.out.println("Input driver's name");
        String name  = s.next();
        System.out.println("Input driver's location");
        String location = s.next();
        System.out.println("Input driver's team");
        String team = s.next();
        int positions[] = new int[10];
        int races =0;
        while(true){
            System.out.println("Choose position from 1 - 10");
            int position = s.nextInt();
            System.out.println("Enter the number of times position " + position + " was obtained.");
            int times = s.nextInt();
            positions[position -1] = times;
            System.out.println("Do you wish to continue yes/no");
            input = s.next();
            if(input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")){
                break;
            }else if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")){
                continue;
            }
        }
        for(int i=0;i<positions.length;i++){
            races = races + positions[i];
        }
        //verifying section
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Team: " + team);

        System.out.println("Add driver to championship (yes/no)");
        input = s.next();
        if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")){
            Formula1Driver driverAdded = new Formula1Driver(name,location,team,races,positions);
            formula1drivers.add(driverAdded); // add driver to list
            sortDrivers();  // sorting driver after updating list

        }else{
            System.out.println("No driver added");

        }


    }

    @Override
    public void removeDriver() {                                                       //Delete a driver and the team that the driver belongs to from the Formula 1 championship.
        System.out.println("Select driver to delete");
        printDriverList();
        String delete = s.next();
        for(Formula1Driver f1 : formula1drivers){
            if (f1.getDriverName().toLowerCase().equals(delete.toLowerCase())){
                    System.out.println("Do you wish to delete: "  + f1.getDriverName());
                    System.out.println("Enter yes/no");
                    String option = s.next();

                    if(option.toLowerCase().equals("yes")){
                        formula1drivers.remove(f1);
                        System.out.println("------------------------------------------------------------------------------");
                        System.out.println(f1.getDriverName() + " was successfully deleted");
                        sortDrivers();
                        return;
                    }else{
                        return;
                    }
            }
        }
        System.out.println(delete + " is not in the F1 championship");
    }
    @Override
    public void viewStatistics() {                                                      //Display the various statistics for a selected existing driver.
        sortDrivers();
        System.out.println("Select driver to statistics of ");
        printDriverList();
        input = s.next();
        for (Formula1Driver f1 : formula1drivers) {
            if (f1.getDriverName().toLowerCase().equals(input.toLowerCase())) {
                System.out.println("name: " + f1.getDriverName());
                System.out.println("location: " + f1.getDriverLocation());
                System.out.println("team: " + f1.getDriverTeam());
                System.out.println("no of races: " + f1.getNoOfRaces());
                System.out.println("points: " + f1.getPoints());
            }
        }
    }
    @Override
    public void changeDriver(){                                                       //Change the driver for an existing constructor team
        System.out.println("Select team to change driver  ");
        printDriverList();
        input = s.next();
        for(Formula1Driver f1 : formula1drivers) {
            if (f1.getDriverName().toLowerCase().equals(input.toLowerCase()) || f1.getDriverTeam().toLowerCase().equals(input.toLowerCase())) {   // user can enter either driver name or team name
                System.out.println("Set new driver for team  " + f1.getDriverTeam());
                input = s.next();
                f1.setDriverName(input);
                sortDrivers();
            }
        }
    }
    @Override
    public void viewFormula1Table(){                                                   //Display the Formula 1 Driver Table
        sortDrivers();
        System.out.println("\n---------------- Scoreboard ----------------\n");
        System.out.println("Name\t\tTeam\t\tPoints\t1STs\t2NDs\t3RD\t ");
        for(Formula1Driver f1 : pointsSort){
            System.out.println( f1.getDriverName() + "\t" +
                                f1.getDriverTeam() + "\t\t" +
                                f1.getPoints() + "\t\t" +
                                f1.getPositions(0)+ "\t\t" +
                                f1.getPositions(1)+ "\t\t" +
                                f1.getPositions(2));

        }
    }
    @Override
    public void addacompletedRace() {                                                         //Add a race completed with its date and the positions that all the drivers achieved.
        System.out.println("Enter date of race in mm-dd-yyyy format: ");
        String dateInput = s.next();
        Date date;
        try{
            date = new SimpleDateFormat("MM-dd-yyyy").parse(dateInput);
        }catch (ParseException ex){
            System.out.println("Invalid Date Format");
            return;
        }



        int i = 0;
        ArrayList<Formula1Driver> f1copy = new ArrayList<>(formula1drivers);
        ArrayList<Formula1Driver> racesPos = new ArrayList<>();
        while(i < formula1drivers.size()){
            for(Formula1Driver drivers : f1copy){
                System.out.println(drivers.getDriverName());
            }
            System.out.println("Enter driver who won position: " + (i+1));
            input = s.next();
            Formula1Driver selectedDriver = null;
            for(Formula1Driver f1 : formula1drivers){
                if(f1.getDriverName().toLowerCase().equals(input.toLowerCase()) && f1copy.contains(f1)){ //Checks if driver is in the league with a set position
                    selectedDriver = formula1drivers.get(formula1drivers.indexOf(f1));
                }
            }
            if(selectedDriver == null || formula1drivers.contains(selectedDriver) == false){
                System.out.println("Invalid Input");
            }else{
                selectedDriver.setNoOfRaces(selectedDriver.getNoOfRaces()+1);
                selectedDriver.addPositions(i);
                selectedDriver.calculatePoints();
                racesPos.add(selectedDriver);
                sortDrivers();  // sorting driver after updating list
                f1copy.removeIf(Formula1Driver -> Formula1Driver.getDriverName().equalsIgnoreCase(input));
                i++;

                //verifying section;



            }
        }
        races.add(new Race(date,racesPos));
    }

    @Override
    public void saveData() throws IOException{                                                  //Saving in a file of all the information entered by the user so far.

            FileOutputStream f = new FileOutputStream(new File("F1Championship.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            for(Formula1Driver f1 : formula1drivers){
                o.writeObject(f1);
            }

            FileOutputStream fr = new FileOutputStream(new File("Race.txt"));
            ObjectOutputStream or = new ObjectOutputStream(fr);

            for(Race r : races){
                or.writeObject(r);
            }


            o.flush();
            f.close();
            o.close();


            or.flush();
            fr.close();
            or.close();


    }
    @Override
    public void loadData() throws  IOException,ClassNotFoundException{                                 //Load saved file
        FileInputStream fi = new FileInputStream("F1Championship.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        for(;;){
            try{
                Formula1Driver f1 =    (Formula1Driver) oi.readObject();
                formula1drivers.add(f1);
            }catch(EOFException e ){
                break;

            }
        }
        fi.close();
        oi.close();


        System.out.println("\nDrivers Loaded Successfully");


        FileInputStream fir = new FileInputStream("Race.txt");
        ObjectInputStream oir = new ObjectInputStream(fir);

        for(;;){
            try{
                Race r =    (Race) oir.readObject();
                races.add(r);
            }catch(EOFException e ){
                break;

            }
        }
        fir.close();
        oir.close();


        System.out.println("Races Loaded Successfully");
        System.out.println("\n---------------------------");

        sortDrivers();  // sorts driver after updating the list


    }


    public void printDriverList() {
        for (int i = 0; i < formula1drivers.size(); i++) {
            System.out.println(i + 1 + ". " + formula1drivers.get(i).getDriverName() + " " + formula1drivers.get(i).getDriverTeam());
        }
    }



    public void sortDrivers(){
        pointsSort = new  ArrayList<>(formula1drivers); // makes sure all new drivers are updated before sorting

        Collections.sort(pointsSort, new Comparator<Formula1Driver>() {
            @Override
            public int compare(Formula1Driver o1, Formula1Driver o2) {
                if(o1.getPoints() > o2.getPoints()){
                    return -1;
                }else if (o1.getPoints() < o2.getPoints()){
                    return 1;
                }else{
                    if(o1.getPositions(0) >o2.getPositions(0)){
                        return -1;
                    }else if (o1.getPositions(0) <o2.getPositions(0)){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }
        });
        }




}


//References
//https://www.w3schools.com/java/java_encapsulation.asp
//https://www.geeksforgeeks.org/getter-and-setter-in-java/
//https://www.javatpoint.com/how-to-sort-an-array-in-java
//https://www.geeksforgeeks.org/arrays-sort-in-java-with-examples/