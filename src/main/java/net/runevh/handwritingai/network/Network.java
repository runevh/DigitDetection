package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Matrix;
import net.runevh.handwritingai.math.Vector;
import net.runevh.handwritingai.network.functions.Activation;
import net.runevh.handwritingai.network.functions.Cost;

public class Network {

    private static Network instance;

    private final java.util.Vector<Layer> layers = new java.util.Vector<>();

    public void addLayer(int neurons, Activation activation){
        layers.add(new Layer(neurons, activation, layers.isEmpty() ? null : layers.get(layers.size() -1)));
    }

    public Network(int inputSize) {
        instance = this;
        addLayer(inputSize, Activation.IDENTITY);
    }

    public Result evaluate(Vector input, Vector expected){
        Vector currentSignal = input;
        for(Layer layer : layers){
            currentSignal = layer.evaluate(currentSignal);
        }
        if(expected != null){
            learnFrom(expected);
            double cost = Cost.getTotal(expected, currentSignal);
            return new Result(currentSignal, cost);
        }

        return new Result(currentSignal);
    }

    public void updateFromLearning() {
        for (Layer l : layers)
            if (l.hasPreLayer())
                l.updateWeightsAndBias();
    }


    private void learnFrom(Vector expected){
        Layer layer = getLayers().lastElement();

        Vector dCost = Cost.getDerivative(expected, layer.getResult());

        while(layer.hasPreLayer()){
            Vector dCostDI = layer.getActivation().dCostDInput(layer.getResult(), dCost);
            Matrix dCostDWeight = dCostDI.outerProduct(layer.getPrev().getResult());

            layer.addDWB(dCostDWeight, dCostDI);

            dCost = layer.getWeights().multiply(dCostDI);
            layer = layer.getPrev();
        }
    }

    public java.util.Vector<Layer> getLayers() {
        return layers;
    }

    public static Network getInstance() {
        return instance;
    }
}
