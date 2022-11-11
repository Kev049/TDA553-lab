import java.awt.*;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

public abstract class Car implements Movable {

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private int directionY;
    private int directionX;
    // private Point position;
    private Point2D.Double position;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;        
        this.directionY = 1;
        this.modelName = modelName;
        this.directionX = 0;
        this.position = new Point2D.Double(0.0, 0.0);
        if (enginePower >= 0) {
            this.enginePower = enginePower;
        } else {
            enginePower = 0;
        }
        stopEngine();
    }

    public void move(){
        if (this.directionX % 2 != 0){
            System.out.println("X");
            this.position.x = roundPosition(this.position.x, this.directionX);
        } else {
            System.out.println("Y");
            this.position.y = roundPosition(this.position.y, this.directionY);
        }
    }
    
    //default rounding mode is HALF_EVEN
    public double roundPosition(double position, int direction) { 
        double temp = position + this.getCurrentSpeed() * direction;
        String s = df.format(temp);
        return Double.parseDouble(s);
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
            this.directionY = 0;
        } else if (this.directionX == -1 && this.directionY == 0) {
            this.directionX = 0;
            this.directionY = 1;
        } else if (this.directionX == 0 && this.directionY == 1) {
            this.directionX = 1;
            this.directionY = 0;
        }
    }

    public void startEngine(){
        double initalSpeed = 0.1;
        if (this.enginePower >= initalSpeed) {
            this.currentSpeed = initalSpeed;
        } else {
            this.currentSpeed = this.enginePower;
        }
    }

    public void stopEngine(){ 
        this.currentSpeed = 0;
    }

    public abstract double speedFactor(); 

    // TODO fix this method according to lab pm
    public void gas(double amount){
        if (amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        }
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }
    }

    public void incrementSpeed(double amount){
        double newSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        if (currentSpeed < newSpeed){
	        currentSpeed = newSpeed;
        }
    }

    public void decrementSpeed(double amount){
        double newSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        if (currentSpeed > newSpeed) {
            currentSpeed = newSpeed;
        }
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

    public Point2D getPosition(){
        return this.position;
    }

    public double getDirectionX(){
        return this.directionX;
    }

    public double getDirectionY(){
        return this.directionY;
    }

    //setters
    public void setColor(Color clr){
	    this.color = clr;
    }

}
