import java.util.ArrayList;

public class NaiveBayes {

	private ArrayList<Instance> trainingSet;
	private ArrayList<Instance> testSet;


	//prior probabilities
	private double probSpam;
	private double probNotSpam;
	//conditional probabilities
	private double[] p_feat_spam;
	private double[] p_feat_notSpam;
	//feature probabilities
	private double[] feature_probabilities;
	//posterior probabilities
	private double p_spam_data;
	private double p_notSpam_data;

	public NaiveBayes(ArrayList<Instance> training, ArrayList<Instance> test) {
		trainingSet = training;
		testSet = test;
		
		// calculate p(spam) and P(not spam)
		calculatePriorProbs();
		// caculate the probabilities of each attribute occuring
		feature_probabilities = calculateFeatureProbs();
		// set guesses
		calculateConditionalProbability();
		for(Instance email : testSet){
			calculatePosterior(email);
		}

	}

	public void calculatePriorProbs() {
		double spamCount = 0;
		double notSpamCount = 0;
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
		double numSpam = 0;
		double numNotSpam = 0;
		double[] numFeatureSpam = new double[trainingSet.size()];
		double[] numFeatureNotSpam = new double[trainingSet.size()];

		for (Instance email : trainingSet) {
			if (email.getLabel() == 1) {
				for (int i = 0; i < email.getAttr().length; i++) {
					if (email.getAttr()[i] == 1) {
						numFeatureSpam[i]++;
					}
				}
				numSpam++;
			} else {
				for (int i = 0; i < email.getAttr().length; i++) {
					if (email.getAttr()[i] == 1) {
						numFeatureNotSpam[i]++;
					}
				}
				numNotSpam++;
			}
		}
		
		for(double d: numFeatureSpam){
			d = d/numSpam;
		}
		for(double d : numFeatureNotSpam){
			d = d/numNotSpam;
		}
		
		p_feat_spam = numFeatureSpam;
		p_feat_notSpam = numFeatureNotSpam;
	}

	public void calculatePosterior(Instance email){
		double normaliser = 1;
		this.p_spam_data = 1;
		this.p_notSpam_data = 1;
		
		for(int i = 0; i < email.getAttr().length; i++){
			if(email.getAttr()[i] == 1){
				p_spam_data *= p_feat_spam[i];
				p_notSpam_data *= p_feat_notSpam[i];
				normaliser *= feature_probabilities[i];
			}
		}

		p_spam_data =  p_spam_data * probSpam;
		p_notSpam_data *= probNotSpam;
		
		p_spam_data /= normaliser;
		p_notSpam_data /= normaliser;
		
		printPosteriors();
	}

	public void printPosteriors() {
		System.out.println("Posteriors:");
		System.out.println("p(Spam|Data) = "  + this.p_spam_data);
		System.out.println("p(Not Spam|Data) = "  + this.p_notSpam_data);
		System.out.println("Sum of posteriors = "  + (this.p_spam_data + this.p_notSpam_data));
		if (this.p_spam_data > this.p_notSpam_data) {
			System.out.println("Spam is more likely.");
		} else if (this.p_spam_data < this.p_notSpam_data) {
			System.out.println("Not Spam is more likely.");
		} else {
			System.out.println("Equal chance of breakdown vs no breakdown.");
		}
		System.out.println(" ");
	}
	
	public void printProbabilities(){
		System.out.println("Priors:");
		System.out.println("p(Spam) = "  + this.probSpam);
		System.out.println("p(Not Spam) = "  + this.probNotSpam);
		
	}

}
