package facility;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import vehicle.CarHolder;
import vehicle.car.Car;

public class CarRepairShop implements CarHolder {
    private List<Car> loadedCars;
    private int maxAmountOfCars;
    private Point2D.Double position;
    private String shopName;

    public CarRepairShop(int max, Point2D.Double position, String shopName) {
        this.maxAmountOfCars = max;
        this.position = position;
        this.shopName = shopName;
        this.loadedCars = new ArrayList<>();
    }

    @Override
    public void loadCar(Car car) {
        if ((this.loadedCars.isEmpty() || this.loadedCars.size() < maxAmountOfCars) && isCarNearby(car)) {
            this.loadedCars.add(car);
        }
    }

    @Override
    public void unloadCar(Car car) {
        this.loadedCars.remove(car);
        Point2D.Double newCarPos = new Point2D.Double(this.getPosition().getX() + 2, this.getPosition().getY());
        car.setPosition(newCarPos);
    }

    @Override
    public boolean isCarNearby(Car car) {
        boolean isCarNearby = false;
        double repairShopPosX = this.getPosition().getX();
        double repairShopPosY = this.getPosition().getY();
        double carPosX = car.getPosition().getX();
        double carPosY = car.getPosition().getY();

        if (repairShopPosX >= carPosX - 1 && repairShopPosX <= carPosX + 1) {
            if (repairShopPosY >= carPosY - 1 && repairShopPosY <= carPosY + 1) {
                isCarNearby = true;
            }
        }

        return isCarNearby;
    }

    @Override
    public List<Car> getLoadedCars() {
        return this.loadedCars;
    }

    public Point2D.Double getPosition() {
        return new Point2D.Double(this.position.x, this.position.y);
    }

    public int getMaxAmountOfCars() {
        return maxAmountOfCars;
    }

    public String getShopName() {
        return shopName;
    }

}