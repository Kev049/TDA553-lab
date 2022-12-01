package facility;

import java.awt.geom.Point2D;

import org.junit.Test;

import vehicle.car.Volvo240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FacilityTestCarRepairShop {

  CarRepairShop carRepairShop = new CarRepairShop(5, new Point2D.Double(1, 1), "Roy & Roger's Repair Shop");

  @Test
  public void propertiesOfCarRepairShopInitialised() {
    assertTrue(carRepairShop.getMaxAmountOfCars() >= 0);
    assertTrue(carRepairShop.getShopName().length() > 0);
  }
  
  @Test
  public void CarRepairshopCanLoadCar(){
    Volvo240 carV = new Volvo240();
    carRepairShop.loadCar(carV);
    assertTrue(carRepairShop.getLoadedCars().contains(carV));
  }

  @Test
  public void CarRepairshopCanUnloadCar() {
    Volvo240 carV = new Volvo240();
    carRepairShop.loadCar(carV);
    carRepairShop.unloadCar(carV);
    assertEquals(0, carRepairShop.getLoadedCars().size());
  }

//Fixa denna! Testar inte bilar som inte ar nearby
  @Test
  public void LoadCarCanOnlyLoadNearbyCar() {
    Point2D.Double repairShopPos = carRepairShop.getPosition();
    Point2D.Double expectedPos = new Point2D.Double(repairShopPos.getX() + 2, repairShopPos.getY());
  
    Volvo240 carV = new Volvo240();
    carRepairShop.loadCar(carV);
    carRepairShop.unloadCar(carV);

    assertEquals(expectedPos, carV.getPosition());
  }

}