package net.runevh.handwritingai.math;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private double[][] a;

    public Matrix(double[][] a) {
        this.a = a;
    }

    public Matrix(int rows, int columns){

        double[][] a = new double[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                a[i][j] = 0;
            }
        }

        this.a = a;
    }

    public void set(int row, int col, double a) {
        this.a[row][col] = a;
    }

    public double get(int row, int col) {
        return this.a[row][col];
    }

    public int getRows() {
        return a.length;
    }

    public int getColumns() {
        return a[0].length;
    }


    /**
     * @param v vector to multiply
     * @return the matrix multiplied with a vector
     */
    public Vector multiply(Vector v) {

        Vector res = new Vector(getRows());

        for (int i = 0; i < getRows(); i++) {
            res.set(i, new Vector(a[i]).dotProduct(v));
        }

        return res;

    }

    /**
     * @param m matrix to add
     * @return two matrices added
     */
    public Matrix add(Matrix m) {

        double[][] r = new double[getRows()][getColumns()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                r[i][j] = get(i, j) + m.get(i, j);
            }
        }

        return new Matrix(r);
    }

    /**
     * @param x scalar
     * @return the matrix mutliplied with a scalar
     */
    public Matrix multiply(double x) {

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                set(i, j, get(i, j) * x);
            }
        }

        return this;
    }

    /**
     * @param m matrix to add
     * @return two matrices added
     */
    public Matrix subtract(Matrix m) {

        double[][] r = new double[m.getRows()][getColumns()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                r[i][j] = get(i, j) - m.get(i, j);
            }
        }

        return new Matrix(r);
    }

    public String toString(){
        StringBuilder s = new StringBuilder("Matrix: {");
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getColumns(); j++){
                s.append(get(i, j)).append(" ,");
            }
            s.append("\n");
        }

        return s.toString();
    }

}
