package vehicle;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

// import vehicle.Car;
// import vehicle.Saab95;

public class CarTestSaab {

  Saab95 sCar = new Saab95();

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
    Saab95 unmovedCar = new Saab95();
    sCar.move();
    assertEquals(unmovedCar.getPosition(), sCar.getPosition());
  }

  @Test
  public void startEngineShouldChangeCurrentSpeed() {
    Saab95 unmovedCar = new Saab95();
    sCar.startEngine();
    assertNotEquals(unmovedCar.getCurrentSpeed(), sCar.getCurrentSpeed());
  }

  @Test
  public void turningOffEngineShouldChangeCurrentSpeedToStationary() {
    Saab95 unmovedCar = new Saab95();
    sCar.startEngine();
    sCar.stopEngine();
    assertEquals(unmovedCar.getCurrentSpeed(), sCar.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void turningShouldChangeDirection() {
    Saab95 unturnedCar = new Saab95();
    sCar.turnLeft();
    assertNotEquals(unturnedCar.getDirection(), sCar.getDirection());
  }

  @Test
  public void carTurningRightTwiceWillHaveTheSameDirectionAsIfItTurnedLeftTwice() {
    Saab95 leftTurningCar = new Saab95();
    sCar.turnRight();
    sCar.turnRight();
    leftTurningCar.turnLeft();
    leftTurningCar.turnLeft();
    assertEquals(leftTurningCar.getDirection(), sCar.getDirection());
  }

  @Test
  public void doesTurnLeftChangeDirectionBy90Degrees() {
    assertEquals(Car.NORTH, sCar.getDirection());
    sCar.turnLeft();
    assertEquals(Car.WEST, sCar.getDirection());
    sCar.turnLeft();
    assertEquals(Car.SOUTH, sCar.getDirection());
    sCar.turnLeft();
    assertEquals(Car.EAST, sCar.getDirection());
  }

  @Test
  public void doesTurnRightChangeDirectionBy90Degrees() {
    assertEquals(Car.NORTH, sCar.getDirection());
    sCar.turnRight();
    assertEquals(Car.EAST, sCar.getDirection());
    sCar.turnRight();
    assertEquals(Car.SOUTH, sCar.getDirection());
    sCar.turnRight();
    assertEquals(Car.WEST, sCar.getDirection());
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
    assertEquals(Car.NORTH, sCar.getDirection());
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

  @Test
  public void OnePercentBrakeReducesSpeedButCarShouldStillBeMoving() {
    sCar.startEngine();
    assertEquals(0.1, sCar.getCurrentSpeed(), 0.000001);
    sCar.brake(0.01);
    assertEquals(0.0875, sCar.getCurrentSpeed(), 0.000001);
  }

  @Test
  public void halfGasShouldIncreaseSpeed() {
    sCar.startEngine();
    sCar.gas(0.5);
    assertEquals(0.725, sCar.getCurrentSpeed(), 0.0001);
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

  @Test
  public void turboIncreasesMaximumOneThatTheCarCanAccelerate() {
    Saab95 carWithTurbo = new Saab95();
    carWithTurbo.setTurboOn();
    sCar.gas(1);
    carWithTurbo.gas(1);
    assertTrue(carWithTurbo.getCurrentSpeed() > sCar.getCurrentSpeed());
  }

  @Test
  public void canTurboBeTurnedOffAfterTurboAcceleration() {
    Saab95 anotherCar = new Saab95();
    anotherCar.setTurboOn();
    sCar.gas(1);
    anotherCar.gas(1);
    double speedWithTurbo = anotherCar.getCurrentSpeed();
    double speedWithoutTurbo = sCar.getCurrentSpeed();

    anotherCar.setTurboOff();
    sCar.gas(1);
    anotherCar.gas(1);
    assertEquals(anotherCar.getCurrentSpeed() - speedWithTurbo, sCar.getCurrentSpeed() - speedWithoutTurbo, 0.0001);
  }

}