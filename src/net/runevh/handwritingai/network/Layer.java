package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Vector2;
import net.runevh.handwritingai.network.functions.Activation;

public class Layer {
    private Layer prev = null;
    private Vector2 result = new Vector2(0, 0);
    private Activation activation;

    double weights;
    double bias;

    public Vector2 evaluate(Vector2 vec){
        result = vec;
        if(prev != null){
            result = new Vector2(activation.getFunction().apply(vec.x * weights + bias), activation.getFunction().apply(vec.y * weights + bias));
        }
        return result;
    }

    public Vector2 getResult() {
        return result;
    }

    public boolean hasPreLayer(){
        return prev == null;
    }

    public Activation getActivation() {
        return activation;
    }
}
