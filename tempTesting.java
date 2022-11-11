import java.util.Locale;

public class tempTesting {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Volvo240 vCar = new Volvo240();
        //Saab95 sCar = new Saab95();

        System.out.println(vCar.getPosition());

        vCar.startEngine();
        vCar.move();
        
        System.out.println(vCar.getPosition());

        System.out.println("\n");

        vCar.gas(1);
        vCar.move();
        System.out.println(vCar.getPosition());
    }
}