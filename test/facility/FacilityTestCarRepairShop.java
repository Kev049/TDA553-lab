package facility;

import org.junit.Test;

import model.facility.CarRepairShop;
import model.vehicle.car.Volvo240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

public class FacilityTestCarRepairShop {

  CarRepairShop carRepairShop = new CarRepairShop(5, new Point2D.Double(1, 1), "Roy & Roger's Repair Shop");

  @Test
  public void propertiesOfCarRepairShopInitialised() {
    assertTrue(carRepairShop.getMaxAmountOfCars() >= 0);
    assertTrue(carRepairShop.getShopName().length() > 0);
  }

  @Test
  public void CarRepairshopCanLoadCar() {
    Volvo240 carV = new Volvo240();
    carRepairShop.loadCar(carV);
    assertTrue(carRepairShop.getLoadedCars().contains(carV));
  }

  @Test
  public void LoadCarCanOnlyLoadNearbyCar() {
    Volvo240 carV = new Volvo240();
    carV.gas(0.75);
    carV.move();
    carV.move();
    carV.move(); // Car Now out of "Nearby" range
    carRepairShop.loadCar(carV);
    assertFalse(carRepairShop.getLoadedCars().contains(carV));
  }

  @Test
  public void CannotLoadMoreThanMaximumNumberOfCars() {
    for (int i = 0; i < 10; i++) {
      carRepairShop.loadCar(new Volvo240());
    }
    assertEquals(5, carRepairShop.getLoadedCars().size());
  }

  @Test
  public void CarRepairshopCanUnloadCar() {
    Volvo240 carV = new Volvo240();
    carRepairShop.loadCar(carV);
    assertEquals(1, carRepairShop.getLoadedCars().size());
    carRepairShop.unloadCar(carV);
    assertEquals(0, carRepairShop.getLoadedCars().size());
  }

  @Test
  public void UnloadCarUnloadsTheCorrectCar() {
    Volvo240 carV = new Volvo240();
    Volvo240 carV2 = new Volvo240();
    carRepairShop.loadCar(carV2);
    carRepairShop.loadCar(carV);
    carRepairShop.unloadCar(carV2);
    assertFalse(carRepairShop.getLoadedCars().contains(carV2));
  }

  @Test
  public void carIsUnloadedAtCorrectPosition() {
    Point2D.Double repairShopPos = carRepairShop.getPosition();
    Point2D.Double expectedPos = new Point2D.Double(repairShopPos.getX() + 2, repairShopPos.getY());

    Volvo240 carV = new Volvo240();
    carRepairShop.loadCar(carV);
    carRepairShop.unloadCar(carV);

    assertEquals(expectedPos, carV.getPosition());
  }

  @Test
  public void CanCreateCarRepairShopsWithDifferentCapacities() {
    CarRepairShop carRepairShop2 = new CarRepairShop(3, new Point2D.Double(2, 2), "Dom's Repair Shop");
    assertNotEquals(carRepairShop.getMaxAmountOfCars(), carRepairShop2.getMaxAmountOfCars());
  }

}