import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Car implements Movable {

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private double directionY;
    private double directionX;
    // private Point position;
    private Point2D.Double position;
    
    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.directionX = 0;
        this.directionY = 0;
        this.position = new Point2D.Double(0.0, 0.0);
        stopEngine();
    }

    public void move(){
        this.position.x += this.getCurrentSpeed() * this.directionX;
        this.position.y += this.getCurrentSpeed() * this.directionY;
    }

    public void turnLeft(){
        if (this.directionX == 1){
            this.directionX = 0;
            this.directionY = 1;
        } 
        else if (this.directionX == 0 && this.directionY == -1){
            this.directionX = 1;
            this.directionY = 0;
        } 
        else if (this.directionX == 0 && this.directionY == 1){
            this.directionX = -1;
            this.directionY = 0;
        }
        else if (this.directionX == -1){
            this.directionX = 0;
            this.directionY = -1;
        }
    }

    public void turnRight(){
        if (this.directionX == 1 && this.directionY == 0) {
            this.directionX = 0;
            this.directionY = -1;
        } else if (this.directionX == 0 && this.directionY == -1) {
            this.directionX = -1;
            this.directionX = 0;
        } else if (this.directionX == -1 && this.directionY == 0) {
            this.directionX = 0;
            this.directionY = 1;
        } else if (this.directionX == 0 && this.directionY == 1) {
            this.directionX = 1;
            this.directionY = 0;
        }
    }

    public void startEngine(){
	    this.currentSpeed = 0.1;
    }

    public void stopEngine(){
	    this.currentSpeed = 0;
    }

    public abstract double speedFactor(); 

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }

    public void incrementSpeed(double amount){
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    //getters
    public int getNrDoors(){
        return this.nrDoors;
    }
    
    public double getEnginePower(){
        return this.enginePower;
    }

    public double getCurrentSpeed(){
        return this.currentSpeed;
    }

    public Color getColor(){
        return this.color;
    }

    //setters
    public void setColor(Color clr){
	    this.color = clr;
    }

}
