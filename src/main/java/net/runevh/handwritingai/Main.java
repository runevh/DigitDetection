package net.runevh.handwritingai;

import net.runevh.handwritingai.files.DataReader;
import net.runevh.handwritingai.files.DataVector;
import net.runevh.handwritingai.math.Vector;
import net.runevh.handwritingai.network.Network;
import net.runevh.handwritingai.network.Result;
import net.runevh.handwritingai.network.functions.Activation;
import net.runevh.handwritingai.ui.graphs.GraphPlotter;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Collections.replaceAll;
import static java.util.Collections.unmodifiableList;

public class Main {

    private static int BATCH_SIZE = 32;

    public static void main(String[] args) {

        DataVector[] d0 = DataReader.getData("src/main/resources/train-images.idx3-ubyte", "src/main/resources/train-labels.idx1-ubyte");
        //DataVector[] d1 = DataReader.getData("src/main/resources/train-images.idx3-ubyte", "src/main/resources/train-labels.idx1-ubyte");

        Network network = new Network(28*28);
        network.addLayer(38, Activation.LEAKY_RELU);
        network.addLayer(12, Activation.LEAKY_RELU);
        network.addLayer(10, Activation.SOFTMAX);

        Thread t = new GraphPlotter();
        t.start();

        while(true){
            int correct = apply(d0, network, true);
            Result.ErrorRate.lastAccuracy = (100d * correct / d0.length);
        }

        //Canvas.init();
    }

    private static List<DataVector> getBatch(int i, List<DataVector> data) {
        int fromIx = i * BATCH_SIZE;
        int toIx = Math.min(data.size(), (i + 1) * BATCH_SIZE);
        return unmodifiableList(data.subList(fromIx, toIx));
    }

    private static int apply(DataVector[] data, Network network, boolean learn){
        AtomicInteger correct = new AtomicInteger();

        for(int i = 0; i < data.length / BATCH_SIZE; i++){

            getBatch(i, Arrays.stream(data).toList()).forEach(img -> {
                Result result = learn ?
                        network.evaluate(img, new Vector(10).set(img.getLabel(), 1)) : network.evaluate(img, null);

                if(result.getSignal().indexOfLargestElement() == img.getLabel())
                    correct.incrementAndGet();
            });

            if (learn) network.updateFromLearning();
        }

        return correct.get();
    }
}
