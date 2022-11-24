package vehicle;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

// import vehicle.Car;
// import vehicle.Scania;

public class VehicleTestScania {

  Scania sCar = new Scania();

  @Test
  public void propertiesOfCarInitialised() {
    assertTrue(sCar.getNrDoors() > 0);
    assertTrue(sCar.getModelName().length() > 0);
    assertTrue(sCar.getColor() instanceof Color);
  }

  @Test
  public void doesTheCarStartFacingNorth() {
    assertEquals(new Point(0, 1), sCar.getDirection());
  }

  @Test
  public void moveShouldDoNothingIfEngineOFF() {
    Scania unmovedCar = new Scania();
    sCar.move();
    assertEquals(unmovedCar.getPosition(), sCar.getPosition());
  }

  @Test
  public void startEngineShouldChangeCurrentSpeed() {
    Scania unmovedCar = new Scania();
    sCar.startEngine();
    assertNotEquals(unmovedCar.getCurrentSpeed(), sCar.getCurrentSpeed());
  }

  @Test
  public void turningOffEngineShouldChangeCurrentSpeedToStationary() {
    Scania unmovedCar = new Scania();
    sCar.startEngine();
    sCar.stopEngine();
    assertEquals(unmovedCar.getCurrentSpeed(), sCar.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void turningShouldChangeDirection() {
    Scania unturnedCar = new Scania();
    sCar.turnLeft();
    assertNotEquals(unturnedCar.getDirection(), sCar.getDirection());
  }

  @Test
  public void carTurningRightTwiceWillHaveTheSameDirectionAsIfItTurnedLeftTwice() {
    Scania leftTurningCar = new Scania();
    sCar.turnRight();
    sCar.turnRight();
    leftTurningCar.turnLeft();
    leftTurningCar.turnLeft();
    assertEquals(leftTurningCar.getDirection(), sCar.getDirection());
  }

  @Test
  public void doesTurnLeftChangeDirectionBy90Degrees() {
    assertEquals(Vehicle.NORTH, sCar.getDirection());
    sCar.turnLeft();
    assertEquals(Vehicle.WEST, sCar.getDirection());
    sCar.turnLeft();
    assertEquals(Vehicle.SOUTH, sCar.getDirection());
    sCar.turnLeft();
    assertEquals(Vehicle.EAST, sCar.getDirection());
  }

  @Test
  public void doesTurnRightChangeDirectionBy90Degrees() {
    assertEquals(Vehicle.NORTH, sCar.getDirection());
    sCar.turnRight();
    assertEquals(Vehicle.EAST, sCar.getDirection());
    sCar.turnRight();
    assertEquals(Vehicle.SOUTH, sCar.getDirection());
    sCar.turnRight();
    assertEquals(Vehicle.WEST, sCar.getDirection());
  }

  @Test
  public void moveAfterTurningOnTheEngineDrivesNorth() {
    sCar.startEngine();
    assertEquals(new Point2D.Double(0.0, 0.0), sCar.getPosition());
    sCar.move();
    assertEquals(new Point2D.Double(0.0, 0.1), sCar.getPosition());
    sCar.move();
    assertEquals(new Point2D.Double(0.0, 0.2), sCar.getPosition());
  }

  @Test
  public void moveAfterTurningLeftDrivesWest() {
    sCar.startEngine();
    sCar.turnLeft();
    sCar.move();
    assertEquals(new Point2D.Double(-0.1, 0.0), sCar.getPosition());
  }

  @Test
  public void moveAfterTurningLeftDrivesEast() {
    sCar.startEngine();
    sCar.turnRight();
    sCar.move();
    assertEquals(new Point2D.Double(0.1, 0.0), sCar.getPosition());
  }

  @Test
  public void moveAfterMakingAUturnDrivesInOppositeDirection() {
    sCar.startEngine();
    assertEquals(Vehicle.NORTH, sCar.getDirection());
    sCar.turnLeft();
    sCar.turnLeft();
    sCar.move();
    assertEquals(new Point2D.Double(0.0, -0.1), sCar.getPosition());
  }

  @Test
  public void maximumBrakeReducesSpeedToZero() {
    sCar.startEngine();
    sCar.move();
    assertEquals(0.1, sCar.getCurrentSpeed(), 0.0001);
    sCar.brake(1);
    assertEquals(0.0, sCar.getCurrentSpeed(), 0.0001);
  }

  //TODO Change variable name to something more suitable
  @Test
  public void OnePercentBrakeReducesSpeedButCarShouldStillBeMoving() { 
    sCar.startEngine();
    assertEquals(0.1, sCar.getCurrentSpeed(), 0.000001);
    sCar.brake(0.01);
    assertEquals(0.09, sCar.getCurrentSpeed(), 0.000001);
  }

  @Test
  public void halfGasShouldIncreaseSpeed() {
    sCar.startEngine();
    sCar.gas(0.5);
    assertTrue((sCar.getCurrentSpeed() > 0.1));
  }

  @Test
  public void fullGasShouldUtiliseMaxEnginePower() {
    sCar.startEngine();
    sCar.gas(1);
    assertEquals(1.35, sCar.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void fullGasWithMoveShouldRepetitvelyIncreasePositionQuickerInSameDirection() {
    sCar.startEngine();
    sCar.gas(1);
    sCar.move();
    assertEquals(new Point2D.Double(0.0, 1.35), sCar.getPosition());
    sCar.gas(1);
    sCar.move();
    assertEquals(new Point2D.Double(0.0, 3.95), sCar.getPosition());
    sCar.gas(1);
    sCar.move();
    assertEquals(new Point2D.Double(0.0, 7.8), sCar.getPosition());
  }

  @Test
  public void maxSpeedShouldNotBeMoreThanEnginePower() {
    double enginePower = sCar.getEnginePower();
    sCar.startEngine();
    while (true) {
      double currentSpeed = sCar.getCurrentSpeed();
      sCar.gas(1);
      double newSpeed = sCar.getCurrentSpeed();
      if (currentSpeed == newSpeed) {
        assertEquals(sCar.getCurrentSpeed(), enginePower, 0.0001);
        break;
      }
    }
  }

}
