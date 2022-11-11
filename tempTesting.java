public class tempTesting {
    public static void main(String[] args) {
        Volvo240 vCar = new Volvo240();
        //Saab95 sCar = new Saab95();

        System.out.println(vCar.getDirectionX());
        System.out.println(vCar.getDirectionY());
        System.out.println(vCar.getPosition());

        vCar.move();
        
        System.out.println(vCar.getDirectionX());
        System.out.println(vCar.getDirectionY());
        System.out.println(vCar.getPosition());
    }
}