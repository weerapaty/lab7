import java.util.Arrays;
import java.util.ArrayList;

public class Lab_MatrixMul {
    public static void main(String ... args) {
        int[][] inputA = {{5, 6, 7}, {4, 8 , 9}};
        int[][] inputB = {{6, 4}, {5, 7}, {1, 1}};

        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);

        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;

        MyData matC = new MyData(matC_r, matC_c);
       
        //Q4
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < matC_r; i++) {
            Thread mat = new Thread(new MatrixMulThread(i, i, matA, matB, matC));
            mat.start();
            threads.add(mat);    
        }
        
        //Q5
        for (Thread t : threads) {
            try {
                t.join();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        matC.show();
    }
}

class MatrixMulThread implements Runnable {
    int pro_Row;
    int pro_Col;
    MyData dataA;
    MyData dataB;
    MyData dataC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
        //Q1
        pro_Row = tRow;
        pro_Col = tCol;
        dataA = a;
        dataB = b;
        dataC = c;
    }

    /*Q2*/ public void run() {

        //Q3
        for (int i = 0; i < dataA.data.length; i++) {
            for (int j = 0; j < dataB.data.length; j++) {
                dataC.data[pro_Row][i] += dataA.data[pro_Row][j] * dataB.data[j][i];
            }
        }
        //System.out.println("perform sum of multiplicataion of assigned row and col");
    }
}   

class MyData {
    int[][] data;

    MyData(int[][] m) {
        data = m;
    }

    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data) {
            Arrays.fill(aRow, 0);
        }
    }

    void show() {
        System.out.println(Arrays.deepToString(data));
    }
}