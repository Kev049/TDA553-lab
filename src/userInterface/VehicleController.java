package userInterface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.vehicle.Vehicle;
import model.vehicle.VehicleFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.awt.geom.Point2D;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehicleController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the
    // statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    VehicleView frame;

    // A list of cars, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    ArrayList<String> vehicleModelNames = new ArrayList<>();

    JButton gasButton;
    JSpinner gasSpinner;
    int gasAmount;

    // methods:

    public VehicleController(ArrayList<String> vehicleModelNames) {
        int i = 1;
        for (String modelName : vehicleModelNames) {
            Vehicle v = VehicleFactory.createVehicle(modelName);
            this.vehicles.add(v);
            v.setPosition(new Point2D.Double(0, 100.0 * i));
            i++;
        }
        this.vehicleModelNames = vehicleModelNames;
        this.frame = new VehicleView("CarSim 1.0", vehicleModelNames);
        this.timer.start();

        gasButton = this.frame.getGasButton();
        gasSpinner = this.frame.getGasSpinner();

        addGasListeners();
    }

    public void addGasListeners() {
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gas(gasAmount);
            }
        });
    }

    /*
     * Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                // vehicle.setPosition(new Point2D.Double(0, 100.0*i));
                vehicle.move();
                int x = (int) Math.round(vehicle.getPosition().getX());
                int y = (int) Math.round(vehicle.getPosition().getY());
                frame.updateVehiclePosition(x, y, vehicle.getModelName());
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }
}
