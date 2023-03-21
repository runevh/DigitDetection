package net.runevh.handwritingai.network;

import net.runevh.handwritingai.math.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Result {

    Vector signal;
    double cost;

    public Result(Vector signal, double cost) {
        this.signal = signal;
        this.cost = cost;
    }

    public Result(Vector signal) {
        this.signal = signal;
        this.cost = Double.NaN;
    }

    public Vector getSignal() {
        return signal;
    }

    public int getResult(){
        int maxI = 0;
        double max = 0;
        for(int i = 0; i < signal.size(); i++){
            double x = Math.abs(signal.get(i));
            if(x > max){
                max = x;
                maxI = i;
            }
        }
        return maxI;
    }

    public static class ErrorRate {

        public static double lastAccuracy = 0;

        private static List<ErrorRate> lastErrorRateData = new ArrayList<>();

        public static List<ErrorRate> getLastErrorRateData() {
            return lastErrorRateData;
        }

        private final double accuracy;
        private final long timeStamp;

        public ErrorRate(double accuracy) {
            this.accuracy = accuracy;
            this.timeStamp = System.currentTimeMillis();

            if(lastErrorRateData.size() > 50)
                lastErrorRateData.remove(0);


            lastErrorRateData.add(this);
        }

        public double getAccuracy() {
            return accuracy;
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public static void init(){
            new ErrorRate(0);
        }
    }
}
