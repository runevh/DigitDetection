package net.runevh.handwritingai;

public class Util {

    public static char getCharFromGray(double x){
        if(x > 1) x = 1;
        // 1 -> 0
        String grayScale = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,^`'. ";
        int index = (int) ((grayScale.length() - 1) - (x * (grayScale.length() - 1)));
        return grayScale.charAt(index);
    }
}
