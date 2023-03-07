package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Vector2;

import java.util.Vector;

public class Network {
    Vector<Layer> layers = new Vector<>();

    public void addLayer(Layer layer){
        layers.add(layer);
    }

    public void evaluate(Vector2 input, Vector2 expected){
        Vector2 currentSignal = input;
        for(Layer layer : layers){
            currentSignal = layer.evaluate(currentSignal);
        }

        return currentSignal;
    }
}
