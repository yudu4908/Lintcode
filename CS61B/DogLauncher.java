public class DogLauncher {
    public static void main(String[] args) {
        //Dog mayaDog = new Dog(10);
        //mayaDog.size = 1000;
        //mayaDog.bark();
        //someDogs is an array contains 2 dogs
        Dog[] someDogs = new Dog[2];
        someDogs[0] = new Dog(10);
        someDogs[1] = new Dog(105);
        someDogs[0].bark();
        someDogs[1].bark();
    }
}