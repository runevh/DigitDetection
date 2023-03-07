package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Vector2;

public class Layer {
    private Layer prev = null;

    public Vector2 evaluate(Vector2 vec){
        Vector2 result = vec;
        if(prev != null){
            result = activation.fn(vec.mul)
        }
    }
}
