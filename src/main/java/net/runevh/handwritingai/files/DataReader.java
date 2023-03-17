package net.runevh.handwritingai.files;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataReader {

    public static DataVector[] getData(String dataPath, String labelPath) {

        DataVector[] data = new DataVector[0];
        try {
            DataInputStream iStream = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath)));
            //Placeholder skip
            iStream.readInt();
            int nItems = iStream.readInt();
            int nRows = iStream.readInt();
            int nCols = iStream.readInt();

            System.out.println("Items: " + nItems);

            DataInputStream lIStream = new DataInputStream(new BufferedInputStream(new FileInputStream(labelPath)));
            lIStream.readInt();

            data = new DataVector[nItems];

            for(int i = 0; i < nItems; i++){
                DataVector v = new DataVector(nRows, nCols);
                v.setLabel(lIStream.readUnsignedByte());
                for (int j = 0; j < nRows; j++) {
                    for (int k = 0; k < nCols; k++) {
                        v.set(j * nCols + k, iStream.readUnsignedByte() / 255d);
                    }
                }
                data[i] = v;
            }

            iStream.close();
            lIStream.close();
        } catch (IOException e){
            e.printStackTrace();
            System.exit(8);
        }

        return data;
    }
}
