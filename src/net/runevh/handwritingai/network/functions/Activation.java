package net.runevh.handwritingai.network.functions;

import net.runevh.handwritingai.math.Matrix;
import net.runevh.handwritingai.math.Vector2;

import java.time.format.TextStyle;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;

public enum Activation {

    //https://himanshuxd.medium.com/activation-functions-sigmoid-relu-leaky-relu-and-softmax-basics-for-neural-networks-and-deep-8d9c70eed91e
    RELU("ReLU", x -> x <= 0 ? 0 : x, x -> x <= 0 ? 0 : 1d),
    LEAKY_RELU("Leaky_ReLU", x -> x <= 0 ? 0.01 * x : x, x -> x <= 0 ? 0.01 : 1),
    SIGMOID("Sigmoid", x -> 1/(1+Math.exp(-x)), x -> (1/(1+Math.exp(-x)))*(1-(1/(1+Math.exp(-x))))),
    SOFTPLUS("Softplus", x -> Math.log(1+ Math.exp(x)), x -> 1/(1+Math.exp(-x))),
    IDENTITY("Identity", x -> x, x -> 1d);

    private final String name;
    private final Function<Double, Double> function;
    private final Function<Double, Double> diffFunction;

    Activation(String name, Function<Double, Double> function, Function<Double, Double> diffFunction){
        this.name = name;
        this.function = function;
        this.diffFunction = diffFunction;
    }

    public Vector2 dCostDInput(Vector2 out, Vector2 cost){
        double x = getDerFunction().apply(out.x) * cost.x;
        double y = getDerFunction().apply(out.y) * cost.y;
        return new Vector2(x, y);
    }

    public String getName() {
        return name;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public Function<Double, Double> getDerFunction() {
        return diffFunction;
    }

    public Vector2 calcFunction(Vector2 vec){
        return new Vector2(getFunction().apply(vec.x), getFunction().apply(vec.y));
    }
}
