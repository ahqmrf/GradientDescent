import java.util.Arrays;

/**
 * Created by bsse0 on 7/23/2017.
 */
public class GradientDescent {
    private double [] theta;
    private double [][] trainingSet;
    private int numberOfVariables;
    private double learningRate;
    private double initTheta;
    private int totalData;

    public GradientDescent(double initTheta) {
        this.initTheta = initTheta;
    }

    public double getHypothesis(int pos) {
        double result = theta[0];
        for (int i = 0; i < numberOfVariables; i++) {
            result += theta[i] * trainingSet[pos][i];
        }
        return result;
    }

    public double getCost(int pos) {
        double result = 0;
        for (int i = 0; i < totalData; i++) {
            result += (-numberOfVariables * 1.0 / totalData) * (trainingSet[i][numberOfVariables] - getHypothesis(i)) * trainingSet[i][pos];
        }
        return result;
    }

    public void update() {
        double[] newTheta = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            newTheta[i] = theta[i] - learningRate * getCost(i);
        }
        System.arraycopy(newTheta, 0, theta, 0, numberOfVariables);
        for (double d : theta) System.out.print(d + " ");
        System.out.println();
    }

    public void train(double learningRate, double[][] trainingSet, int iterations) {
        initTrainingSet(trainingSet);
        this.learningRate = learningRate;
        theta = new double[numberOfVariables];
        Arrays.fill(theta, 0.0);
        theta[0] = initTheta;
        while (iterations > 0) {
            update();
            iterations--;
        }
    }

    private void initTrainingSet(double[][] trainingSet) {
        totalData = trainingSet.length;
        numberOfVariables = trainingSet[0].length;
        this.trainingSet = new double[totalData][numberOfVariables + 1];
        for (int i = 0; i < totalData; i++) {
            this.trainingSet[i][0] = 1.0;
            for (int j = 0; j < numberOfVariables; j++) {
                this.trainingSet[i][j + 1] = trainingSet[i][j];
            }
        }
    }

    public void setTheta(double[] theta) {
        this.theta = theta;
    }

    public double getResult(double [] x) {
        double result = theta[0];
        for (int i = 1; i < numberOfVariables; i++) {
            result += theta[i] * x[i - 1];
        }
        return result;
    }
}
