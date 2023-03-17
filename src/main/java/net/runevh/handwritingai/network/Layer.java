package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Matrix;
import net.runevh.handwritingai.math.Vector;
import net.runevh.handwritingai.network.functions.Activation;

import java.util.Random;

public class Layer {
    private final Layer prev;
    private Vector result;
    private final Activation activation;
    private final static Random rand = new Random();

    private double learningRate = 0.005;

    Matrix weights;
    Vector bias;
    Matrix deltaWeights;
    double deltaWeightsAdded;
    Vector deltaBias;
    double deltaBiasAdded;

    public Layer(int nNeurons, Activation activation, Layer prev){
        this.activation = activation;
        this.result = new Vector(nNeurons);
        this.prev = prev;
        this.weights = new Matrix(prev == null ? 1: prev.getResult().size(), nNeurons);

        //Set weights to random values to small values
        for (int i = 0; i < weights.getRows(); i++) {
            for (int j = 0; j < weights.getColumns(); j++) {
                final double factor = Math.sqrt(2.0 / (weights.getColumns() + weights.getRows()));
                weights.set(i, j, rand.nextGaussian() * factor);
            }
        }

        this.bias = new Vector(nNeurons);
        this.deltaWeights = new Matrix(prev == null ? 1: prev.getResult().size(), nNeurons);
        this.deltaBias = new Vector(nNeurons);
    }

    public synchronized Vector evaluate(Vector vec){
        result = vec.copy();
        if(hasPreLayer()){
            result = activation.calcFunction(vec.multiply(weights).add(bias));
        }
        return result;
    }

    public Vector getResult() {
        return result;
    }

    public Layer getPrev() {
        return prev;
    }

    public void updateWeights(Matrix dCost){
        weights = weights.subtract(dCost.multiply(learningRate));
    }

    public void updateBias(Vector dCost){
        bias.subtract(dCost.multiply(learningRate));
    }

    //add deltaWeight and delta Bias
    public void addDWB(Matrix dWeight, Vector dBias){
        this.deltaWeights = deltaWeights.add(dWeight);
        this.deltaBias = deltaBias.add(dBias);

        this.deltaWeightsAdded++;
        this.deltaBiasAdded++;
    }

    public void updateWeightsAndBias(){
        if(deltaWeightsAdded > 0){
            Matrix averageDW = deltaWeights.multiply(1/deltaWeightsAdded);
            updateWeights(averageDW);
            deltaWeights = new Matrix(deltaWeights.getRows(), deltaWeights.getColumns());
            deltaWeightsAdded = 0;
        }

        if(deltaBiasAdded > 0){
            Vector averageB = deltaBias.multiply(1/deltaBiasAdded);
            updateBias(averageB);
            deltaBias = new Vector(deltaBias.size());
            deltaBiasAdded = 0;
        }
    }

    public boolean hasPreLayer(){
        return prev != null;
    }

    public synchronized void addDeltaWeightsAndBiases(Matrix dW, Vector dB) {
        deltaWeights.add(dW);
        deltaWeightsAdded++;
        deltaBias = deltaBias.add(dB);
        deltaBiasAdded++;
    }

    public Activation getActivation() {
        return activation;
    }

    public Matrix getWeights() {
        return weights;
    }
}