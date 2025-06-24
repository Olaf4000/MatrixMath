public class Main {
    public static void main(String[] args) {
        double[][] matrix1 = new double[][]{
                {3, 2, 5},
                {4, 5, 7}
        };



        Matrix m1 = new Matrix(matrix1);
        m1.printMatrixVisual();

       m1.scaleMatrix(new double[]{2,2,2});
       m1.printMatrixVisual();

    }
}