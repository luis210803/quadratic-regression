public class Main {
    public static void main(String[] args) {
        double[][] data = DataSet.getData();
        double[] coefficients = DiscrethMath.calculateCoefficients(data);

        double B0 = coefficients[0];
        double B1 = coefficients[1];
        double B2 = coefficients[2];

        System.out.println("B0 = " + B0);
        System.out.println("B1 = " + B1);
        System.out.println("B2 = " + B2);
    }
}
