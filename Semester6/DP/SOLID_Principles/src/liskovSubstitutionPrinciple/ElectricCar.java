package liskovSubstitutionPrinciple;

/*
* By throwing a car without an engine into the mix, we are inherently changing the behavior of our program.
* This is a blatant violation of Liskov substitution and is a bit harder to fix than our previous two principles.
* One possible solution would be to rework our model into interfaces that take into account the engine-less state of our Car.
* */
public class ElectricCar implements Car {

    public void turnOnEngine() {
        throw new AssertionError("I don't have an engine!");
    }

    public void accelerate() {
        //this acceleration is crazy!
    }
}