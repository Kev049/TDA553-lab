package vehicle;

public class ScaniaPlatform implements IPlatform {

    private double currentAngle;
    private double elavationSpeed = 5;

    public ScaniaPlatform() {
        currentAngle = 0;
    }

    public double getAngle() {
        return this.currentAngle;
    }

    
    public void lower() {
        double flatbedAngle = Math.max(this.currentAngle - elavationSpeed,0);
        if (flatbedAngle < 0) {
            this.currentAngle = 0;
        } else {
            this.currentAngle = flatbedAngle;
        }
    }

    public void raise() {
        double flatbedAngle = Math.min(this.currentAngle + elavationSpeed,0.7);
        if (flatbedAngle > 70) {
            this.currentAngle = 70;
        } else {
            this.currentAngle += flatbedAngle;
        }
    }
}
