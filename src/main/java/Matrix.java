import lombok.Getter;

import java.util.Arrays;

@Getter
public class Matrix {
    private final int xLength; //amount of columns
    private final int yLength; //amount of rows
    private double[][] matrix;

    public Matrix(int xLength, int yLength) {
        this.xLength = xLength;
        this.yLength = yLength;

        matrix = new double[xLength][yLength];
        fillMatrix(0.0);
    }

    private void fillMatrix(double fillValue) {
        for (int i = 0; i < xLength; i++) {
            Arrays.fill(matrix[i], fillValue);
        }
    }

    public Matrix(double[][] pMatrix) {
        this.xLength = pMatrix.length;
        this.yLength = pMatrix[0].length;
        matrix = pMatrix;
    }

    public void setMatrix(double[][] matrix) {
        if (matrix.length != xLength || matrix[0].length != yLength) {
            throw new IllegalArgumentException();
        }

        this.matrix = matrix;
    }

    public double[] getColumnVector(int columnIndex) {
        double[] vektor = new double[yLength];

        for (int i = 0; i < yLength; i++) {
            vektor[i] = matrix[columnIndex][i];
        }

        return vektor;
    }

    public double[] getRowVector(int rowIndex) {
        double[] vektor = new double[xLength];

        for (int i = 0; i < xLength; i++) {
            vektor[i] = matrix[i][rowIndex];
        }

        return vektor;
    }

    public void setValue(int columnIndex, int rowIndex, double value) {
        matrix[columnIndex][rowIndex] = value;
    }

    public void printMatrixVisual() {
        for (int row = 0; row < this.yLength; row++) {
            for (int column = 0; column < this.xLength; column++) {
                System.out.print(matrix[column][row] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setIdentity() {
        setIdentity(1);
    }

    public void setIdentity(double pIdentityValue){
        if (this.xLength != this.yLength) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < xLength; i++) {
            this.matrix[i][i] = pIdentityValue;
        }
    }

    public Matrix getHomogenisedMatrix() {
        Matrix homogenisedMatrix = new Matrix(this.xLength, this.yLength + 1);
        homogenisedMatrix.fillMatrix(1.0);

        for (int column = 0; column < this.xLength; column++) {
            for (int row = 0; row < this.yLength; row++) {
                homogenisedMatrix.setValue(column, row, this.matrix[column][row]);
            }
        }

        return homogenisedMatrix;
    }

    private Matrix deHomogeniseMatrix(Matrix homogenisedMatrix) {
        Matrix deHomogenisedMatrix = new Matrix(homogenisedMatrix.xLength, homogenisedMatrix.yLength - 1);

        for (int column = 0; column < deHomogenisedMatrix.xLength; column++) {
            for (int row = 0; row < deHomogenisedMatrix.yLength; row++) {
                deHomogenisedMatrix.setValue(column, row, homogenisedMatrix.getMatrix()[column][row]);
            }
        }

        return deHomogenisedMatrix;
    }

    //Matrix Transformation
    public void translateMatrix(double[] translationVector) {
        if (translationVector.length != this.yLength) {
            throw new IllegalArgumentException();
        }

        Matrix translatedHomogenisedMatrix = MatrixOperations.multiplyMatrix(
                this.getHomogenisedMatrix(),
                TransformationMatrixFactory.getTranslationMatrix(translationVector)
        );

        Matrix translatedMatrix = deHomogeniseMatrix(translatedHomogenisedMatrix);
        this.matrix = translatedMatrix.getMatrix();
    }

    public void scaleMatrix(double[] scalingVector) {
        if (scalingVector.length != this.yLength) {
            throw new IllegalArgumentException();
        }

        Matrix scaledHomogenisedMatrix = MatrixOperations.multiplyMatrix(
                this.getHomogenisedMatrix(),
                TransformationMatrixFactory.getScalingMatrix(scalingVector)
        );

        Matrix translatedMatrix = deHomogeniseMatrix(scaledHomogenisedMatrix);
        this.matrix = translatedMatrix.getMatrix();
    }
}
