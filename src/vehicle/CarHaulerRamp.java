package vehicle;


public class CarHaulerRamp implements IPlatform {

    private Boolean rampDown;

    public CarHaulerRamp() {
        this.rampDown = false;
    }

    public Boolean isRampDown() {
        return this.rampDown;
    }

    public void lower() {
        this.rampDown = true;
    }

    public void raise() {;
        this.rampDown = false;
    }
}
