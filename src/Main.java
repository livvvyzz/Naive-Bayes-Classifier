import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static String trainingFile = "spamLabelled.dat";
	private String testFile = "spamUnlabelled.dat";

	private static ArrayList<Instance> instances;
	public static void main(String[] args) {
		// read training data
		File file = new File(trainingFile);
		dataReader(file);
	}

	public static void dataReader(File file) {
		try {
			Scanner scan = new Scanner(file);
			System.out.println("Reading data from " + file + "...");
			instances = new ArrayList<Instance>();
			while (scan.hasNextLine()) {
				char[] line = scan.nextLine().toCharArray();
				int[] data = new int[12];
				for(int i = 0; i < 12; i++){
					data[i] = line[i];
				}
				int label = line[12];
				Instance inst = new Instance(data, label);
				instances.add(inst);
			}
			System.out.println(instances.get(0).getAttr().toString());
		} catch (FileNotFoundException e) {
			System.out.print("Data reader could not read file");
			e.printStackTrace();
		}
	}

}
