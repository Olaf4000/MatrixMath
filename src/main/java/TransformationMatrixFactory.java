public class TransformationMatrixFactory {
    public static Matrix getTransformationMatrix(double[] translationVector) {
        Matrix translationMatrix = new Matrix(translationVector.length + 1, translationVector.length + 1);
        translationMatrix.setIdentity();

        for (int i = 0; i < translationVector.length; i++) {
            translationMatrix.setValue(translationMatrix.getXLength() - 1, i, translationVector[i]);
        }

        return translationMatrix;
    }
}
