package userInterface;

import java.util.ArrayList;

public class CarSimulatorApp {
    public static void main(String[] args) {

        ArrayList<String> vehicleModelNames = new ArrayList<>();
        vehicleModelNames.add("Volvo240");
        vehicleModelNames.add("Saab95");
        vehicleModelNames.add("Scania");

        // Instance of this class
        VehicleController vc = new VehicleController(vehicleModelNames);
        //vc.frame = new VehicleView("CarSim 1.0", vc);
    }
}
