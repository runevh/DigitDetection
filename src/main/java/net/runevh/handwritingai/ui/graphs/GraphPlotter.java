package net.runevh.handwritingai.ui.graphs;

import net.runevh.handwritingai.network.Result;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class GraphPlotter extends Thread {

    static long startTime = System.currentTimeMillis();

    @Override
    public void run() {

        Result.ErrorRate.init();
        double[][] initdata = getData();

        final XYChart chart = QuickChart.getChart("Live training improvement", "Seconds", "Accuracy in %", "Accuracy", initdata[0], initdata[1]);

        chart.getStyler().setYAxisMax(100d);
        chart.getStyler().setYAxisMin(0d);
        final SwingWrapper<XYChart> sw = new SwingWrapper<>(chart);
        sw.displayChart();

        while (true) {

            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Result.ErrorRate err = new Result.ErrorRate(Result.ErrorRate.lastAccuracy);
            long ms = err.getTimeStamp() - startTime;
            int seconds = (int) (ms / 1000);
            System.out.println("Accuracy after " + seconds + " seconds: " + err.getAccuracy());

            final double[][] data = getData();

            javax.swing.SwingUtilities.invokeLater(() -> {
                chart.updateXYSeries("Accuracy", data[0], data[1], null);
                sw.repaintChart();
            });
        }

    }

    private static double[][] getData() {
        double[] xData = new double[Result.ErrorRate.getLastErrorRateData().size()];
        double[] yData = new double[Result.ErrorRate.getLastErrorRateData().size()];

        for(int i = 0; i < Result.ErrorRate.getLastErrorRateData().size(); i++){
            Result.ErrorRate err = Result.ErrorRate.getLastErrorRateData().get(i);
            long ms = err.getTimeStamp() - startTime;
            int seconds = (int) (ms / 1000);
            xData[i] = seconds;
            yData[i] = err.getAccuracy();
        }
        return new double[][] { xData, yData };
    }


}
