public class MatrixOperations {
    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
        if (m1.getYLength() != m2.getYLength() && m1.getYLength() != m2.getXLength()) {
            throw new IllegalArgumentException("Matrix does not have the same number of rows and columns");
        }

        Matrix resultMatrix = new Matrix(m1.getXLength(), m2.getYLength());

        for (int columnIndex = 0; columnIndex < resultMatrix.getXLength(); columnIndex++){
            for (int rowIndex = 0; rowIndex < resultMatrix.getYLength(); rowIndex++){
                if (m1.getColumnVector(columnIndex).length != m2.getRowVector(rowIndex).length){
                    throw new IllegalArgumentException("Matrix does not have the same number of columns and rows");
                }

                double value = 0.0;
                for (int i = 0; i < m2.getXLength(); i++){
                    value += m1.getColumnVector(columnIndex)[i] * m2.getRowVector(rowIndex)[i];
                }
                resultMatrix.setValue(columnIndex, rowIndex, value);
            }
        }

        return resultMatrix;
    }
}
