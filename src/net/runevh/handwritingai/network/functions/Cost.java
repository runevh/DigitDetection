package net.runevh.handwritingai.network.functions;

import net.runevh.handwritingai.math.Vector2;

public class Cost {

    public static double getTotal(Vector2 expected, Vector2 actual){
        Vector2 diff = actual.subtract(expected);
        return diff.dotProduct(diff);
    }

    public static Vector2 getDerivative(Vector2 expected, Vector2 actual){
        return actual.subtract(expected).multiply(2);
    }
}
