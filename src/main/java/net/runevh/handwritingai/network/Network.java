package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Matrix;
import net.runevh.handwritingai.math.Vector;
import net.runevh.handwritingai.network.functions.Activation;
import net.runevh.handwritingai.network.functions.Cost;

public class Network {

    private final java.util.Vector<Layer> layers = new java.util.Vector<>();

    public void addLayer(int neurons, Activation activation){
        layers.add(new Layer(neurons, activation, layers.isEmpty() ? null : layers.get(layers.size() -1)));
    }

    public Network(int inputSize) {
        addLayer(inputSize, Activation.IDENTITY);
    }

    public Result evaluate(Vector input, Vector expected){
        Vector currentSignal = input;

        for(Layer layer : layers){
            currentSignal = layer.evaluate(currentSignal);
        }


        if(expected != null){
            learn(expected);
            double cost = Cost.getTotalCost(expected, currentSignal);
            return new Result(currentSignal, cost);
        }

        return new Result(currentSignal);
    }

    public void update() {
        for (Layer l : layers) if (l.hasPreLayer()) l.updateWeightsAndBias();
    }


    private void learn(Vector expected){
        Layer layer = getLayers().lastElement();

        Vector dCost = Cost.getDerivative(expected, layer.getResult());

        while(layer.hasPreLayer()){
            Vector dCostMD = layer.getActivation().multiplyByDerivative(layer.getResult(), dCost);
            Matrix dCostMDWeight = dCostMD.outerProduct(layer.getPrev().getResult());

            layer.addDWB(dCostMDWeight, dCostMD);

            dCost = layer.getWeights().multiply(dCostMD);
            layer = layer.getPrev();
        }
    }

    public java.util.Vector<Layer> getLayers() {
        return layers;
    }
}
