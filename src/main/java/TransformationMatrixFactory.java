public class TransformationMatrixFactory {
    public static Matrix getTranslationMatrix(double[] translationVector) {
        Matrix translationMatrix = new Matrix(translationVector.length + 1, translationVector.length + 1);
        translationMatrix.setIdentity();

        for (int i = 0; i < translationVector.length; i++) {
            translationMatrix.setValue(translationMatrix.getXLength() - 1, i, translationVector[i]);
        }

        return translationMatrix;
    }

    public static Matrix getScalingMatrix(double[] scalingVector) {
        Matrix scalingMatrix = new Matrix(scalingVector.length + 1, scalingVector.length + 1);
        scalingMatrix.setIdentity();

        for (int i = 0; i < scalingVector.length; i++) {
            scalingMatrix.setValue(i, i, scalingVector[i]);
        }

        return scalingMatrix;
    }
}
