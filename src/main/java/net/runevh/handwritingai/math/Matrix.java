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

    public void set(double[][] a) {
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
     * @param m matrix to multiply
     * @return return two matrices multiplied
     */
    public Matrix multiply(Matrix m) {

        if (getColumns() != m.getRows()) {
            throw new RuntimeException("Can't multiply this matrix. (Rows not equal to columns)");
        }

        double[][] r = new double[m.getRows()][getColumns()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                for (int k = 0; k < m.getRows(); k++) {
                    r[i][j] += a[i][k] * m.a[k][j];
                }
            }
        }

        return new Matrix(r);

    }

    public Matrix invertRowsAndColumns(){
        double[][] nA = new double[getColumns()][getRows()];
        for(int i = 0; i < getColumns(); i++){
            for(int j = 0; j < getRows(); j++){
                nA[i][j] = get(j, i);
            }
        }
        this.a = nA;
        return this;
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
     * @param x scalar
     * @return the matrix with and added scalar
     */
    public Matrix add(double x) {

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                set(i, j, get(i, j) + x);
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

    /**
     * @return a matrix with sigmoid functions
     * σ(z)=1/(1+e^(−z))
     */
    public Matrix sigmoid(){
        double[][] r = new double[getRows()][getColumns()];

        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getColumns(); j++){
                r[i][j] = 1/(1+Math.exp(-get(i,j)));
            }
        }
        return new Matrix(r);
    }

    public static Matrix fromArray(double[] a){
        double[][] r = new double[a.length][0];
        r[0] = a;
        return new Matrix(r);
    }

    public List<Double> toList(){
        List<Double> list = new ArrayList<>();
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getColumns(); j++){
                list.add(get(i, j));
            }
        }

        return list;
    }

    public double getAverage(){
        List<Double> list = toList();
        double sum = 0;
        for(double d : list){
            sum += d;
        }

        return sum/list.size();
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
