package car;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Locale;

public class CarTestVolvo {
  
  Volvo240 vCar = new Volvo240();
  
  @BeforeEach
  public void init() {
    Locale.setDefault(Locale.US);
  }

  @Test
  public void propertiesOfCarInitialised() {
    assertTrue(vCar.getNrDoors() > 0);
    assertTrue(vCar.getModelName().length() > 0);
    assertTrue(vCar.getColor() instanceof Color);
  }

  @Test
  public void doesTheCarStartFacingNorth() {
    assertEquals(new Point(0, 1), vCar.getDirection());
  }

  @Test
  public void moveShouldDoNothingIfEngineOFF(){
    Volvo240 unmovedCar = new Volvo240();
    vCar.move();
    assertEquals(unmovedCar.getPosition(), vCar.getPosition());
  }

  @Test
  public void startEngineShouldChangeCurrentSpeed(){
    Volvo240 unmovedCar = new Volvo240();
    vCar.startEngine();
    assertNotEquals(unmovedCar.getCurrentSpeed(), vCar.getCurrentSpeed());
  }

  @Test
  public void turningOffEngineShouldChangeCurrentSpeedToStationary(){
    Volvo240 unmovedCar = new Volvo240();
    vCar.startEngine();
    vCar.stopEngine();
    assertEquals(unmovedCar.getCurrentSpeed(), vCar.getCurrentSpeed(), 0.0001);
  }

  @Test
  public void turningShouldChangeDirection(){
    Volvo240 unturnedCar = new Volvo240();
    vCar.turnLeft();
    assertNotEquals(unturnedCar.getDirection(), vCar.getDirection());
  }

  @Test
  public void carTurningRightTwiceWillHaveTheSameDirectionAsIfItTurnedLeftTwice(){
    Volvo240 leftTurningCar = new Volvo240();
    vCar.turnRight();
    vCar.turnRight();
    leftTurningCar.turnLeft();
    leftTurningCar.turnLeft();
    assertEquals(leftTurningCar.getDirection(), vCar.getDirection());
  }

  @Test
  public void doesTurnLeftChangeDirectionBy90Degrees() {
    assertEquals(Car.NORTH, vCar.getDirection());
    vCar.turnLeft();
    assertEquals(Car.WEST, vCar.getDirection());
    vCar.turnLeft();
    assertEquals(Car.SOUTH, vCar.getDirection());
    vCar.turnLeft();
    assertEquals(Car.EAST, vCar.getDirection());
  }

  @Test
  public void doesTurnRightChangeDirectionBy90Degrees(){
    assertEquals(Car.NORTH, vCar.getDirection());
    vCar.turnRight();
    assertEquals(Car.EAST, vCar.getDirection());
    vCar.turnRight();
    assertEquals(Car.SOUTH, vCar.getDirection());
    vCar.turnRight();
    assertEquals(Car.WEST, vCar.getDirection());
  }

}
