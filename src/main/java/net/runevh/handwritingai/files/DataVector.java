package net.runevh.handwritingai.files;

import net.runevh.handwritingai.Util;
import net.runevh.handwritingai.math.Matrix;
import net.runevh.handwritingai.math.Vector;

public class DataVector extends Vector {

    private int label;
    //initialize & store when needed
    private Matrix imageMatrix = null;

    private final int rows;
    private final int cols;

    public DataVector(int rows, int cols) {
        super(rows * cols);
        this.rows = rows;
        this.cols = cols;
    }

    public int getLabel() {
        return label;
    }

    public Matrix getPixelMatrix(){
        if(imageMatrix == null) initImageMatrix();
        return imageMatrix;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void initImageMatrix(){
        imageMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                imageMatrix.set(i, j, this.get(i * cols + j));
            }
        }
    }

    public void print(){
        System.out.println("-------------------------------------------------");
        System.out.println("Label: " + label);
        Matrix m = getPixelMatrix();
        for(int i = 0; i < rows; i++){
            StringBuilder builder = new StringBuilder();
            for(int j = 0; j < cols; j++){
                builder.append(Util.getCharFromGray(m.get(i, j)));
            }
            System.out.println(builder);
        }
    }
}
