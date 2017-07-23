import java.io.*;
import java.util.ArrayList;

/**
 * Created by bsse0 on 7/23/2017.
 */
public class Main {

    private static double[][] trainingSet;

    public static void main(String[] args) {
        if(prepareData()) {
            GradientDescent gd = new GradientDescent(0);
            gd.train(0.0001, trainingSet, 1000);
            System.out.println(gd.getResult(new double[]{68.76573426962166}));
        }
    }

    private static boolean prepareData() {
        ArrayList<String> dataString = new ArrayList<>();
        File file = new File("F:\\8th semester\\GradientDescent\\src\\data.txt");
        if(file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                try {
                    while((line = reader.readLine()) != null) {
                        dataString.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (dataString.size() > 0) {
            int totalData = dataString.size();
            int count = dataString.get(0).split(",").length;
            trainingSet = new double[totalData][count];
            for(int i = 0; i < totalData; i++) {
                String[] tokens = dataString.get(i).split(",");
                for (int j = 0; j < count; j++) {
                    trainingSet[i][j] = Double.valueOf(tokens[j]);
                }
            }
            return true;
        }
        return false;
    }
}
