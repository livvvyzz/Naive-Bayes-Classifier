import java.util.ArrayList;

public class NaiveBayes {

	private ArrayList<Instance> trainingSet;
	private ArrayList<Instance> testSet;

	private double[] feature_probabilities;

	private double probSpam;
	private double probNotSpam;

	public NaiveBayes(ArrayList<Instance> training, ArrayList<Instance> test) {
		trainingSet = training;
		testSet = test;

		// calculate p(spam) and P(not spam)
		calculatePriorProbs();
		// caculate the probabilities of each attribute occuring
		feature_probabilities = calculateFeatureProbs();
		// set guesses
		calculateConditionalProbability();

		test();
		testSet();
	}

	public void calculatePriorProbs() {
		int spamCount = 0;
		int notSpamCount = 0;
		for (Instance i : trainingSet) {
			if (i.getLabel() == 1) {
				spamCount++;
			} else {
				notSpamCount++;
			}
		}

		this.probSpam = spamCount / trainingSet.size();
		this.probNotSpam = notSpamCount / trainingSet.size();
	}

	public double[] calculateFeatureProbs() {

		// get the count for each attr
		double[] featureProbs = new double[trainingSet.get(0).getAttr().length];
		// initialise all counts to 1
		for (double d : featureProbs) {
			d = 1;
		}
		// get counts
		for (int i = 0; i < trainingSet.size(); i++) {
			for (int j = 0; j < trainingSet.get(0).getAttr().length; j++) {
				if (trainingSet.get(i).getAttr()[j] == 1) {
					featureProbs[j]++;
				}
			}
		}

		// divide each num by number of instances to get prob
		for (int i = 0; i < featureProbs.length; i++) {
			featureProbs[i] = featureProbs[i] / trainingSet.size();
		}
		return featureProbs;
	}

	public void calculateConditionalProbability() {
		
	}

	public double getProb(int index, int num, int spam) {
		double count = 0;
		for (int i = 0; i < trainingSet.size(); i++) {
			if (trainingSet.get(i).getAttr()[index] == num && trainingSet.get(i).getLabel() == spam) {
				count++;
			}
		}
		return count / trainingSet.size();
	}

	public void test() {
		double count = 0;
		for (Instance email : trainingSet) {
			if (email.getLabel() == email.getGuess()) {
				count++;
				System.out.println(email.getLabel() + "  " + email.getGuess());
			}
		}
		double prob = count / trainingSet.size();
		System.out.print(prob);
	}

	public void testSet() {
		for (Instance email : testSet) {
			conditionalProbability(email);
			if (email.getGuess() == 1) {
				System.out.println("spam");
			} else if (email.getGuess() == 0) {
				System.out.println("nt spam");
			}
		}
	}

}
