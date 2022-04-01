package liskovSubstitutionPrinciple;

/*
 if class A is a subtype of class B, we should be able to replace B with A without disrupting the behavior of our program.
* */
public interface Car {

    void turnOnEngine();
    void accelerate();
}