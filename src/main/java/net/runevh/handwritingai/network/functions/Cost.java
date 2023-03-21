package net.runevh.handwritingai.network.functions;

import net.runevh.handwritingai.math.Vector;

public class Cost {

    /**
     * Cost function: C = ∑(y−exp)^2
     */
    public static double getTotalCost(Vector expected, Vector actual){
        Vector diff = actual.subtract(expected);
        return diff.dotProduct(diff);
    }

    public static Vector getDerivative(Vector expected, Vector actual){
        return actual.subtract(expected).multiply(2);
    }
}
