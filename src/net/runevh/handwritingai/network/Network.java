package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Vector2;
import net.runevh.handwritingai.network.functions.Cost;

import java.util.Vector;

public class Network {

    private static Network instance;

    private final Vector<Layer> layers = new Vector<>();

    public void addLayer(Layer layer){
        layers.add(layer);
    }

    public void evaluate(Vector2 input, Vector2 expected){
        Vector2 currentSignal = input;
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

    private void learnFrom(Vector2 expected){
        Layer layer = Network.getInstance().getLayers().lastElement();

        Vector2 dCost = Cost.getDerivative(expected, layer.getResult());

        while(layer.hasPreLayer()){
            Vector2 dCostDI = layer.getActivation().
        }
    }

    public Vector<Layer> getLayers() {
        return layers;
    }

    public static Network getInstance() {
        return instance;
    }
}
