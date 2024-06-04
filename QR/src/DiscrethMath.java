public class DiscrethMath {
    // Método para calcular los coeficientes de la regresión cuadrática
    public static double[] calculateCoefficients(double[][] data) {
        double sumX = 0, sumY = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumXY = 0, sumX2Y = 0;
        int n = data.length;

        for (int i = 0; i < n; i++) {
            double x = data[i][0];
            double y = data[i][1];
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumX3 += x * x * x;
            sumX4 += x * x * x * x;
            sumXY += x * y;
            sumX2Y += x * x * y;
        }

        double[][] coefficients = {
                {n, sumX, sumX2},
                {sumX, sumX2, sumX3},
                {sumX2, sumX3, sumX4}
        };

        double[] constants = {sumY, sumXY, sumX2Y};

        double[] result = solveLinearSystem(coefficients, constants);

        return result;
    }

    private static double[] solveLinearSystem(double[][] coefficients, double[] constants) {
        int n = constants.length;

        for (int pivot = 0; pivot < n - 1; pivot++) {
            for (int row = pivot + 1; row < n; row++) {
                double factor = coefficients[row][pivot] / coefficients[pivot][pivot];
                for (int col = pivot; col < n; col++) {
                    coefficients[row][col] -= factor * coefficients[pivot][col];
                }
                constants[row] -= factor * constants[pivot];
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = constants[i];
            for (int j = i + 1; j < n; j++) {
                sum -= coefficients[i][j] * solution[j];
            }
            solution[i] = sum / coefficients[i][i];
        }
        return solution;
    }
}

