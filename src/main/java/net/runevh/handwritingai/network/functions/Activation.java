package net.runevh.handwritingai.network.functions;

import net.runevh.handwritingai.math.Vector;

import java.util.function.Function;

public class Activation {

    //https://himanshuxd.medium.com/activation-functions-sigmoid-relu-leaky-relu-and-softmax-basics-for-neural-networks-and-deep-8d9c70eed91e
    public static final Activation RELU = new Activation(x -> x <= 0 ? 0 : x, x -> x <= 0 ? 0 : 1d);
    public static final Activation LEAKY_RELU = new Activation(x -> x <= 0 ? 0.01 * x : x, x -> x <= 0 ? 0.01 : 1);
    public static final Activation SIGMOID = new Activation(x -> 1/(1+Math.exp(-x)), x -> (1/(1+Math.exp(-x)))*(1-(1/(1+Math.exp(-x)))));
    public static final Activation SOFTPLUS = new Activation(x -> Math.log(1+ Math.exp(x)), x -> 1/(1+Math.exp(-x)));
    public static final Activation IDENTITY = new Activation(x -> x, x -> 1d);

    public static final Activation SOFTMAX = new Activation(null, null){

        @Override
        public Vector dCostDInput(Vector out, Vector c){
            double x = out.elementProduct(c).totalSum();
            return out.elementProduct(c.copy().subtract(x));
        }

        @Override
        public Vector calcFunction(Vector vec){
            double[] a = vec.toArray();
            double sum = 0;
            double max = vec.getMax();
            for(double x : a){
                sum += Math.exp(x - max);
            }

            double[] res = new double[vec.size()];
            for(int i = 0; i < res.length; i++){
                res[i] = Math.exp(a[i] - max) / sum;
            }

            return new Vector(res);
        }
    };

    private final Function<Double, Double> function;
    private final Function<Double, Double> diffFunction;

    Activation(Function<Double, Double> function, Function<Double, Double> diffFunction){
        this.function = function;
        this.diffFunction = diffFunction;
    }

    public Vector dCostDInput(Vector out, Vector cost){
        return cost.elementProduct(calcDiffFunction(out));
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public Function<Double, Double> getDerFunction() {
        return diffFunction;
    }

    public Vector calcFunction(Vector vec){
        double[] a = new double[vec.size()];
        for(int i = 0; i < vec.size(); i++){
            a[i] = function.apply(vec.get(i));
        }
        return new Vector(a);
    }

    public Vector calcDiffFunction(Vector vec){
        double[] a = new double[vec.size()];
        for(int i = 0; i < vec.size(); i++){
            a[i] = diffFunction.apply(vec.get(i));
        }
        return new Vector(a);
    }
}
