package net.runevh.handwritingai.math;

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

    public Vector2 multiply(Vector2 vec){
        this.x *= vec.x;
        this.y *= vec.y;
        return this;
    }

    public Vector2 outerProduct(Vector2 vec){

    }

    //https://en.wikipedia.org/wiki/Outer_product

}
