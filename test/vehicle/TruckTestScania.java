package vehicle;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import vehicle.truck.Scania;
import java.awt.*;
import java.awt.geom.Point2D;

public class TruckTestScania {

  Scania sTruck = new Scania();

  @Test
  public void propertiesOfCarInitialised() {
    assertTrue(sTruck.getNrDoors() > 0);
    assertTrue(sTruck.getModelName().length() > 0);
    assertTrue(sTruck.getColor() instanceof Color);
  }

  @Test
  public void doesTheCarStartFacingNorth() {
    assertEquals(new Point(0, 1), sTruck.getDirection());
  }

  @Test
  public void moveShouldDoNothingIfEngineOFF() {
    Scania unmovedCar = new Scania();
    sTruck.move();
    assertEquals(unmovedCar.getPosition(), sTruck.getPosition());
  }

  @Test
  public void startEngineShouldChangeCurrentSpeed() {
    Scania unmovedCar = new Scania();
    sTruck.startEngine();
    assertNotEquals(unmovedCar.getCurrentSpeed(), sTruck.getCurrentSpeed());
  }

  @Test
  public void turningOffEngineShouldChangeCurrentSpeedToStationary() {
    Scania unmovedCar = new Scania();
    sTruck.startEngine();
    sTruck.stopEngine();
    assertEquals(unmovedCar.getCurrentSpeed(), sTruck.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void turningShouldChangeDirection() {
    Scania unturnedCar = new Scania();
    sTruck.turnLeft();
    assertNotEquals(unturnedCar.getDirection(), sTruck.getDirection());
  }

  @Test
  public void carTurningRightTwiceWillHaveTheSameDirectionAsIfItTurnedLeftTwice() {
    Scania leftTurningCar = new Scania();
    sTruck.turnRight();
    sTruck.turnRight();
    leftTurningCar.turnLeft();
    leftTurningCar.turnLeft();
    assertEquals(leftTurningCar.getDirection(), sTruck.getDirection());
  }

  @Test
  public void doesTurnLeftChangeDirectionBy90Degrees() {
    assertEquals(Vehicle.NORTH, sTruck.getDirection());
    sTruck.turnLeft();
    assertEquals(Vehicle.WEST, sTruck.getDirection());
    sTruck.turnLeft();
    assertEquals(Vehicle.SOUTH, sTruck.getDirection());
    sTruck.turnLeft();
    assertEquals(Vehicle.EAST, sTruck.getDirection());
  }

  @Test
  public void doesTurnRightChangeDirectionBy90Degrees() {
    assertEquals(Vehicle.NORTH, sTruck.getDirection());
    sTruck.turnRight();
    assertEquals(Vehicle.EAST, sTruck.getDirection());
    sTruck.turnRight();
    assertEquals(Vehicle.SOUTH, sTruck.getDirection());
    sTruck.turnRight();
    assertEquals(Vehicle.WEST, sTruck.getDirection());
  }

  @Test
  public void moveAfterTurningOnTheEngineDrivesNorth() {
    sTruck.startEngine();
    assertEquals(new Point2D.Double(0.0, 0.0), sTruck.getPosition());
    sTruck.move();
    assertEquals(new Point2D.Double(0.0, 0.1), sTruck.getPosition());
    sTruck.move();
    assertEquals(new Point2D.Double(0.0, 0.2), sTruck.getPosition());
  }

  @Test
  public void moveAfterTurningLeftDrivesWest() {
    sTruck.startEngine();
    sTruck.turnLeft();
    sTruck.move();
    assertEquals(new Point2D.Double(-0.1, 0.0), sTruck.getPosition());
  }

  @Test
  public void moveAfterTurningLeftDrivesEast() {
    sTruck.startEngine();
    sTruck.turnRight();
    sTruck.move();
    assertEquals(new Point2D.Double(0.1, 0.0), sTruck.getPosition());
  }

  @Test
  public void moveAfterMakingAUturnDrivesInOppositeDirection() {
    sTruck.startEngine();
    assertEquals(Vehicle.NORTH, sTruck.getDirection());
    sTruck.turnLeft();
    sTruck.turnLeft();
    sTruck.move();
    assertEquals(new Point2D.Double(0.0, -0.1), sTruck.getPosition());
  }

  @Test
  public void maximumBrakeReducesSpeedToZero() {
    sTruck.startEngine();
    sTruck.move();
    assertEquals(0.1, sTruck.getCurrentSpeed(), 0.0001);
    sTruck.brake(1);
    assertEquals(0.0, sTruck.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void OnePercentBrakeReducesSpeedButCarShouldStillBeMoving() {
    sTruck.startEngine();
    assertEquals(0.1, sTruck.getCurrentSpeed(), 0.000001);
    sTruck.brake(0.01);
    assertEquals(0.0875, sTruck.getCurrentSpeed(), 0.000001);
  }

  @Test
  public void halfGasShouldIncreaseSpeed() {
    sTruck.startEngine();
    sTruck.gas(0.5);
    assertTrue((sTruck.getCurrentSpeed() > 0.1));
  }

  @Test
  public void fullGasShouldUtiliseMaxEnginePower() {
    sTruck.startEngine();
    sTruck.gas(1);
    assertEquals(1.35, sTruck.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void fullGasWithMoveShouldRepetitvelyIncreasePositionQuickerInSameDirection() {
    sTruck.startEngine();
    sTruck.gas(1);
    sTruck.move();
    assertEquals(new Point2D.Double(0.0, 1.35), sTruck.getPosition());
    sTruck.gas(1);
    sTruck.move();
    assertEquals(new Point2D.Double(0.0, 3.95), sTruck.getPosition());
    sTruck.gas(1);
    sTruck.move();
    assertEquals(new Point2D.Double(0.0, 7.8), sTruck.getPosition());
  }

  @Test
  public void maxSpeedShouldNotBeMoreThanEnginePower() {
    double enginePower = sTruck.getEnginePower();
    sTruck.startEngine();
    while (true) {
      double currentSpeed = sTruck.getCurrentSpeed();
      sTruck.gas(1);
      double newSpeed = sTruck.getCurrentSpeed();
      if (currentSpeed == newSpeed) {
        assertEquals(sTruck.getCurrentSpeed(), enginePower, 0.0001);
        break;
      }
    }
  }

  @Test
  public void scaniaShouldNotMoveIfPlatformIsRaised() {
    sTruck.raisePlatform();
    sTruck.startEngine();
    sTruck.move();
    assertEquals(new Point2D.Double(0.0, 0.0), sTruck.getPosition());
  }

  @Test
  public void platformShouldNotRiseIfScaniaIsMoving() {
    sTruck.startEngine();
    sTruck.gas(0.25);
    sTruck.move();
    sTruck.raisePlatform();
    assertTrue(sTruck.isRampDown());
  }

  @Test
  public void scaniaPlatformShouldNotBeRaisedMoreThan70Degrees() {
    for (int i = 0; i < 20; i++) {
      sTruck.raisePlatform();
    }
    assertEquals(70, sTruck.getAngle());
  }

  @Test
  public void platformShouldInitiallyBeDown() {
    assertTrue(sTruck.isRampDown());
  }

  @Test
  public void platformShouldBeOperableAfterScaniaHasBecomeStationary() {
    sTruck.startEngine();
    sTruck.gas(0.25);
    sTruck.move();
    sTruck.stopEngine();
    sTruck.raisePlatform();
    assertFalse(sTruck.isRampDown());
  }

}
