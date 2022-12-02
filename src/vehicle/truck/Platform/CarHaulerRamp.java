package vehicle.truck.Platform;

public class CarHaulerRamp implements IPlatform {

    private Boolean rampDown;

    public CarHaulerRamp() {
        this.rampDown = false;
    }

    @Override
    public void raise() {
        this.rampDown = false;
    }

    @Override
    public void lower() {
        this.rampDown = true;
    }

    @Override
    public boolean isRampDown() {
        return this.rampDown;
    }

}
