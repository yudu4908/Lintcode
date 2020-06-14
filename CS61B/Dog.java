public class Dog {
    public int size;
    //this is a constructor tells us how to construct a dog from our ideal notion of dogness.
    /**This is equivalent of in python doing:
     *  __init__(self, s):
     */
    public Dog(int s) {
        //size = s;
        this.size = s; //this is equivalent of self in python 
    }

    public void bark() {
        if (size < 20) {
            System.out.println("Ayayayayyaa");
        } else if (size < 50) {
            System.out.println("Bark.");
        } else {
            System.out.println("Awooooooooooooooo");
        }
    }

    public static Dog maxDog(Dog d1, Dog d2) {
        if (d1.size > d2.size) {
            return d1;
        }
        return d2;
    }

    public Dog maxDogComparedToMe(Dog otherDog) {
        if (size > otherDog.size) {
            return this; //"this" is me
        }
        return otherDog;
    }
    
}