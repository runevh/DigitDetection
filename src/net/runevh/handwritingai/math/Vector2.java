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

    public double dotProduct(Vector2 vec){
        return this.x * vec.x + this.y * vec.y;
    }

    public Vector2 outerProduct(Vector2 vec){

    }

    //https://en.wikipedia.org/wiki/Outer_product

}
