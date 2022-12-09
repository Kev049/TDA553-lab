package userInterface;

import java.util.ArrayList;

public class CarSimulatorApp {
    public static void main(String[] args) {

        ArrayList<String> vehicleModelNames = new ArrayList<>();
        vehicleModelNames.add("Volvo240");
        vehicleModelNames.add("Saab95");
        vehicleModelNames.add("Scania");

        new VehicleController(vehicleModelNames);
    }
}
