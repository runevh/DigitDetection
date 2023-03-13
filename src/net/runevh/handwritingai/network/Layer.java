package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Matrix;
import net.runevh.handwritingai.math.Vector2;
import net.runevh.handwritingai.network.functions.Activation;

public class Layer {
    private Layer prev = null;
    private Vector2 result = new Vector2(0, 0);
    private Activation activation;

    private double learningRate;

    Matrix weights;
    Vector2 bias;
    Matrix deltaWeights;
    double deltaWeightsAdded;
    Vector2 deltaBias;
    double deltaBiasAdded;

    public Vector2 evaluate(Vector2 vec){
        result = vec.copy();
        if(prev != null){
            result = activation.calcFunction(result.multiply(weights).add(bias));
        }
        return result;
    }

    public Vector2 getResult() {
        return result;
    }

    public Layer getPrev() {
        return prev;
    }

    public void updateWeights(Matrix dCost){
        weights.subtract(dCost.multiply(learningRate));
    }

    public void updateBias(Vector2 dCost){
        bias.subtract(dCost.multiply(learningRate));
    }

    public void addDWB(Matrix dWeight, Vector2 dBias){
        //TODO: Gradient weight
        this.deltaWeights = deltaWeights.add(dWeight.multiply(learningRate));
        this.deltaBias = deltaBias.add(dBias.multiply(learningRate));
    }

    public void updateWeightsAndBias(){
        if(deltaWeightsAdded > 0){
            Matrix averageDW = deltaWeights.multiply(1/deltaWeightsAdded);
            updateWeights(averageDW);
            deltaWeights.multiply(0);
            deltaWeightsAdded = 0;
        }

        if(deltaBiasAdded > 0){
            Vector2 averageB = deltaBias.multiply(1/deltaBiasAdded);
            updateBias(averageB);
            deltaBias.multiply(0);
            deltaBiasAdded = 0;
        }
    }

    public boolean hasPreLayer(){
        return prev == null;
    }

    public Activation getActivation() {
        return activation;
    }

    public Matrix getWeights() {
        return weights;
    }
}
