package vehicle;

import java.awt.*;
import java.awt.geom.Point2D;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import vehicle.truck.CarHauler;
import vehicle.car.Volvo240;
import vehicle.truck.Scania;

public class TruckTestCarHauler {

  CarHauler cHTruck = new CarHauler();

  @Test
  public void propertiesOfCarInitialised() {
    assertTrue(cHTruck.getNrDoors() > 0);
    assertTrue(cHTruck.getModelName().length() > 0);
    assertTrue(cHTruck.getColor() instanceof Color);
  }

  @Test
  public void doesTheCarStartFacingNorth() {
    assertEquals(new Point(0, 1), cHTruck.getDirection());
  }

  @Test
  public void moveShouldDoNothingIfEngineOFF() {
    CarHauler unmovedCar = new CarHauler();
    cHTruck.move();
    assertEquals(unmovedCar.getPosition(), cHTruck.getPosition());
  }

  @Test
  public void startEngineShouldChangeCurrentSpeed() {
    CarHauler unmovedCar = new CarHauler();
    cHTruck.startEngine();
    assertNotEquals(unmovedCar.getCurrentSpeed(), cHTruck.getCurrentSpeed());
  }

  @Test
  public void turningOffEngineShouldChangeCurrentSpeedToStationary() {
    CarHauler unmovedCar = new CarHauler();
    cHTruck.startEngine();
    cHTruck.stopEngine();
    assertEquals(unmovedCar.getCurrentSpeed(), cHTruck.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void turningShouldChangeDirection() {
    CarHauler unturnedCar = new CarHauler();
    cHTruck.turnLeft();
    assertNotEquals(unturnedCar.getDirection(), cHTruck.getDirection());
  }

  @Test
  public void carTurningRightTwiceWillHaveTheSameDirectionAsIfItTurnedLeftTwice() {
    CarHauler leftTurningCar = new CarHauler();
    cHTruck.turnRight();
    cHTruck.turnRight();
    leftTurningCar.turnLeft();
    leftTurningCar.turnLeft();
    assertEquals(leftTurningCar.getDirection(), cHTruck.getDirection());
  }

  @Test
  public void doesTurnLeftChangeDirectionBy90Degrees() {
    assertEquals(Vehicle.NORTH, cHTruck.getDirection());
    cHTruck.turnLeft();
    assertEquals(Vehicle.WEST, cHTruck.getDirection());
    cHTruck.turnLeft();
    assertEquals(Vehicle.SOUTH, cHTruck.getDirection());
    cHTruck.turnLeft();
    assertEquals(Vehicle.EAST, cHTruck.getDirection());
  }

  @Test
  public void doesTurnRightChangeDirectionBy90Degrees() {
    assertEquals(Vehicle.NORTH, cHTruck.getDirection());
    cHTruck.turnRight();
    assertEquals(Vehicle.EAST, cHTruck.getDirection());
    cHTruck.turnRight();
    assertEquals(Vehicle.SOUTH, cHTruck.getDirection());
    cHTruck.turnRight();
    assertEquals(Vehicle.WEST, cHTruck.getDirection());
  }

  @Test
  public void moveAfterTurningOnTheEngineDrivesNorth() {
    cHTruck.startEngine();
    assertEquals(new Point2D.Double(0.0, 0.0), cHTruck.getPosition());
    cHTruck.move();
    assertEquals(new Point2D.Double(0.0, 0.1), cHTruck.getPosition());
    cHTruck.move();
    assertEquals(new Point2D.Double(0.0, 0.2), cHTruck.getPosition());
  }

  @Test
  public void moveAfterTurningLeftDrivesWest() {
    cHTruck.startEngine();
    cHTruck.turnLeft();
    cHTruck.move();
    assertEquals(new Point2D.Double(-0.1, 0.0), cHTruck.getPosition());
  }

  @Test
  public void moveAfterTurningLeftDrivesEast() {
    cHTruck.startEngine();
    cHTruck.turnRight();
    cHTruck.move();
    assertEquals(new Point2D.Double(0.1, 0.0), cHTruck.getPosition());
  }

  @Test
  public void moveAfterMakingAUturnDrivesInOppositeDirection() {
    cHTruck.startEngine();
    assertEquals(Vehicle.NORTH, cHTruck.getDirection());
    cHTruck.turnLeft();
    cHTruck.turnLeft();
    cHTruck.move();
    assertEquals(new Point2D.Double(0.0, -0.1), cHTruck.getPosition());
  }

  @Test
  public void maximumBrakeReducesSpeedToZero() {
    cHTruck.startEngine();
    cHTruck.move();
    assertEquals(0.1, cHTruck.getCurrentSpeed(), 0.0001);
    cHTruck.brake(1);
    assertEquals(0.0, cHTruck.getCurrentSpeed(), 0.0001);
  }

  //TODO Change variable name to something more suitable
  @Test
  public void OnePercentBrakeReducesSpeedButCarShouldStillBeMoving() { 
    cHTruck.startEngine();
    assertEquals(0.1, cHTruck.getCurrentSpeed(), 0.000001);
    cHTruck.brake(0.01);
    assertEquals(0.0875, cHTruck.getCurrentSpeed(), 0.000001);
  }

  @Test
  public void halfGasShouldIncreaseSpeed() {
    cHTruck.startEngine();
    cHTruck.gas(0.5);
    assertTrue((cHTruck.getCurrentSpeed() > 0.1));
  }

  @Test
  public void fullGasShouldUtiliseMaxEnginePower() {
    cHTruck.startEngine();
    cHTruck.gas(1);
    assertEquals(1.35, cHTruck.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void fullGasWithMoveShouldRepetitvelyIncreasePositionQuickerInSameDirection() {
    cHTruck.startEngine();
    cHTruck.gas(1);
    cHTruck.move();
    assertEquals(new Point2D.Double(0.0, 1.35), cHTruck.getPosition());
    cHTruck.gas(1);
    cHTruck.move();
    assertEquals(new Point2D.Double(0.0, 3.95), cHTruck.getPosition());
    cHTruck.gas(1);
    cHTruck.move();
    assertEquals(new Point2D.Double(0.0, 7.8), cHTruck.getPosition());
  }

  @Test
  public void maxSpeedShouldNotBeMoreThanEnginePower() {
    double enginePower = cHTruck.getEnginePower();
    cHTruck.startEngine();
    while (true) {
      double currentSpeed = cHTruck.getCurrentSpeed();
      cHTruck.gas(1);
      double newSpeed = cHTruck.getCurrentSpeed();
      if (currentSpeed == newSpeed) {
        assertEquals(cHTruck.getCurrentSpeed(), enginePower, 0.0001);
        break;
      }
    }
  }

  @Test
  public void CarHaulerShouldNotMoveIfPlatformIsLowered(){
    cHTruck.lowerPlatform();
    cHTruck.startEngine();
    cHTruck.move();
    assertEquals(new Point2D.Double(0.0,0.0), cHTruck.getPosition());
  }

  @Test
  public void platformShouldNotRiseIfCarHaulerIsMoving(){
    cHTruck.startEngine();
    cHTruck.gas(0.25);
    cHTruck.move();
    cHTruck.lowerPlatform();
    assertFalse(cHTruck.isRampDown());
  }

  @Test
  public void platformShouldInitiallyBeDown() {
    assertFalse(cHTruck.isRampDown());
  }
  
  @Test
  public void platformShouldBeOperableAfterCarHaulerHasBecomeStationary() {
    cHTruck.startEngine();
    cHTruck.gas(0.25);
    cHTruck.move();
    cHTruck.stopEngine();
    cHTruck.lowerPlatform();
    assertTrue(cHTruck.isRampDown());
  }
  
  @Test
  public void CarHaulerCanLoadCar(){
    Volvo240 carV = new Volvo240();
    cHTruck.lowerPlatform();
    cHTruck.loadCar(carV);
    assertTrue(cHTruck.getLoadedCars().contains(carV));
  }

  @Test
  public void CarHaulerCanUnloadCar() {
    Volvo240 carV = new Volvo240();
    cHTruck.lowerPlatform();
    cHTruck.loadCar(carV);
    cHTruck.unloadCar();
    assertEquals(0, cHTruck.getLoadedCars().size());
  }

  @Test
  public void DoesUnloadedCarGoBehindCarHauler() {
    Point2D.Double carHaulerPos = cHTruck.getPosition();
    Point2D.Double expectedPos = new Point2D.Double(carHaulerPos.getX(), carHaulerPos.getY() - 1);
    
    Volvo240 carV = new Volvo240();
    cHTruck.lowerPlatform();
    cHTruck.loadCar(carV);
    cHTruck.unloadCar();

    assertEquals(expectedPos, carV.getPosition());
  }

  @Test
  public void CannotUnloadCarIfRampDownIsFalse() {
    Volvo240 carV = new Volvo240();
    cHTruck.loadCar(carV);
    assertTrue(cHTruck.getLoadedCars().size() == 0);
  }

  @Test 

}
