package net.runevh.handwritingai.math;

import java.util.Vector;

public class Vector2 {

    public double x, y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 vec){
        this.x += vec.x;
        this.y += vec.y;
        return this;
    }

    public Vector2 add(double i){
        this.x += i;
        this.y += i;
        return this;
    }

    public Vector2 subtract(Vector2 vec){
        this.x -= vec.x;
        this.y -= vec.y;
        return this;
    }

    public Vector2 multiply(Vector2 vec){
        this.x *= vec.x;
        this.y *= vec.y;
        return this;
    }

    public Vector2 multiply(double i){
        this.x *= i;
        this.y *= i;
        return this;
    }

    public Vector2 multiply(Matrix m){
        for (int j = 0; j < m.getColumns(); j++) {
            double sum = 0.0;
            for (int i = 0; i < m.getRows(); i++) {
                sum += this.get(i) * m.get(i, j);
            }
            this.set(j, sum);
        }

        return this;
    }

    public double dotProduct(Vector2 vec){
        return this.x * vec.x + this.y * vec.y;
    }

    public double[] toArray(){
        return new double[]{x, y};
    }

    public double get(int i){
        return toArray()[i];
    }

    public void set(int i, double v){
        if(i == 0){
            x = v;
        } else if(i == 1){
            y = v;
        }
    }

    public Matrix outerProduct(Vector2 vec){
        double[] u = toArray();
        double[] v = vec.toArray();
        double[][] a = new double[u.length][v.length];
        for(int i = 0; i < u.length; i++){
            for(int j = 0; j < v.length; j++){
                a[i][j] = u[i]*v[j];
            }
        }
        return new Matrix(a);
    }

    public Vector2 copy(){
        return new Vector2(this.x, this.y);
    }
    //https://en.wikipedia.org/wiki/Outer_product

}
