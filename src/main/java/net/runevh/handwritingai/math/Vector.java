package net.runevh.handwritingai.math;

import java.util.Arrays;
import java.util.function.Function;

public class Vector {

    private double[] a;

    public Vector(double... a){
        this.a = a;
    }

    public Vector(int size){
        this.a = new double[size];
        Arrays.fill(a, 0);
    }

    public Vector add(Vector vec){
        for(int i = 0; i < a.length; i++){
            a[i] += vec.a[i];
        }
        return this;
    }

    public Vector subtract(double x){
        for(int i = 0; i < a.length; i++){
            a[i] -= x;
        }
        return this;
    }

    public Vector subtract(Vector vec){

        double[] res = new double[size()];

        for(int i = 0; i < a.length; i++){
            res[i] = a[i] - vec.a[i];
        }
        return new Vector(res);
    }

    public Vector multiply(double x){
        for(int i = 0; i < a.length; i++){
            a[i] *= x;
        }
        return this;
    }

    public Vector multiply(Matrix m){

        double[] res = new double[m.getColumns()];

        for (int j = 0; j < m.getColumns(); j++) {
            res[j] = 0.0;
            for (int i = 0; i < m.getRows(); i++) {
                res[j] += this.get(i) * m.get(i, j);
            }
        }

        return new Vector(res);
    }

    public double dotProduct(Vector vec){
        double sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += this.a[i] * vec.get(i);
        }
        return sum;
    }

    public double[] toArray(){
        return a;
    }

    public double get(int i){
        return a[i];
    }

    public Vector set(int i, double v){
        a[i] = v;
        return this;
    }

    public Matrix outerProduct(Vector vec){
        double[][] res = new double[vec.size()][size()];

        for(int i = 0; i < size(); i++){
            for(int j = 0; j < vec.size(); j++){
                res[j][i] = get(i) * vec.get(j);
            }
        }
        return new Matrix(res);
    }

    public int size(){
        return a.length;
    }

    public Vector copy(){
        return new Vector(a);
    }

    public String toString(){
        StringBuilder b = new StringBuilder("{");
        for(int i = 0; i < a.length; i++){
            b.append(a[i]);
            if(i != a.length - 1) b.append(", ");
        }

        b.append("}");
        return b.toString();
    }

    public double totalSum(){

        double sum = 0;
        for (double v : a) sum += v;

        return sum;
    }

    public double getMax(){
        double max = Double.NaN;
        for (double v : a) {
            if (Double.isNaN(max) || v > max) max = v;
        }
        return max;
    }

    public Vector elementProduct(Vector v) {

        double[] result = new double[size()];

        for (int i = 0; i < a.length; i++)
            result[i] = a[i] * v.a[i];

        return new Vector(result);
    }

    public int indexOfLargestElement() {
        int ixOfLargest = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] > a[ixOfLargest]) ixOfLargest = i;
        return ixOfLargest;
    }

}
