package model.vehicle;

import model.vehicle.car.Saab95;
import model.vehicle.car.Volvo240;
import model.vehicle.truck.Scania;

public class VehicleFactory {

    public static Vehicle createVehicle(String model) throws IllegalArgumentException {
        if (model.equals("Volvo240")) {
            return new Volvo240();
        } else if (model.equals("Saab95")) {
            return new Saab95();
        } else if (model.equals("Scania")) {
            return new Scania();
        }
        throw new IllegalArgumentException("Invalid Model Name Provided");
    }

}