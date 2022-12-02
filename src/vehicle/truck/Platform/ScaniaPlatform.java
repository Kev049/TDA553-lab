package vehicle.truck.Platform;

public class ScaniaPlatform implements IPlatform {

    private int currentAngle;
    private double elavationSpeed = 5;

    public ScaniaPlatform() {
        currentAngle = 0;
    }

    public int getAngle() {
        return this.currentAngle;
    }

    @Override
    public void raise() {
        int flatbedAngle = (int) Math.min(this.currentAngle + elavationSpeed, 70);
        this.currentAngle = flatbedAngle;
    }

    @Override
    public void lower() {
        int flatbedAngle = (int) Math.max(this.currentAngle - elavationSpeed, 0);
        this.currentAngle = flatbedAngle;
    }

    @Override
    public boolean isRampDown() {
        if (currentAngle == 0) {
            return true;
        } else {
            return false;
        }
    }
}
