import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static String trainingFile = "spamLabelled.dat";
	private static String testFile = "spamUnlabelled.dat";

	private static ArrayList<Instance> instances = new ArrayList<Instance>();
	private static ArrayList<Instance> instancesTest = new ArrayList<Instance>();

	public static void main(String[] args) {
		// read training data
		File file = new File(trainingFile);
		dataReader(file, true);
		File test = new File(testFile);
		dataReader(test, false);
		NaiveBayes nb = new NaiveBayes(instances, instancesTest);

	}

	public static void dataReader(File file, boolean b) {
		try {
			Scanner scan = new Scanner(file);
			System.out.println("Reading data from " + file + "...");
			while (scan.hasNextInt()) {

				int[] data = new int[12];
				for (int i = 0; i < 12; i++) {
					data[i] = scan.nextInt();
				}
				//for training set
				if (b) {
					int label = scan.nextInt();
					Instance inst = new Instance(data, label);
					instances.add(inst);
				}
				else {
					Instance inst = new Instance(data);
					instancesTest.add(inst);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.print("Data reader could not read file");
			e.printStackTrace();
		}
	}

}
