package vehicle;

import java.util.List;

import vehicle.car.Car;

public interface CarHolder {

    public void loadCar(Car car);

    public void unloadCar(Car car);

    public List<Car> getLoadedCars();

    public boolean isCarNearby(Car car);

}
