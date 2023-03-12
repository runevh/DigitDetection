package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Matrix;
import net.runevh.handwritingai.math.Vector2;
import net.runevh.handwritingai.network.functions.Cost;

public class Result {

    Vector2 signal;
    double cost;

    public Result(Vector2 signal, double cost) {
        this.signal = signal;
        this.cost = cost;
    }

    public Result(Vector2 signal) {
        this.signal = signal;
        this.cost = Double.NaN;
    }
}
