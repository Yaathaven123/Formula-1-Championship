import java.io.Serializable;

//creating an abstract class
abstract public class Driver  implements Serializable  {
    private String driverName;
    private String driverLocation;
    private String driverTeam;


    //driver constructor
    public Driver(String driverName, String driverLocation, String driverTeam) {
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.driverTeam = driverTeam;

    }

    //setter
    public void setDriverName(String name){
        driverName = name;
    }
    public void setDriverLocation(String location) {
        driverLocation = location;
    }
    public void setDriverTeam(String team) {
        driverTeam = team;
    }

    //getter
    public String getDriverName() {
        return driverName;
    }
    public String getDriverLocation() {
        return driverLocation;
    }
    public String getDriverTeam() { return driverTeam; }

    public String toString(){
        return "Driver Name=" + driverName +
                "Driver Team=" +driverTeam +
                "Driver Location=" +driverLocation;

    }
}
