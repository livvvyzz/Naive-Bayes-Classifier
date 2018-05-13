import java.util.ArrayList;

public class NaiveBayes {

	private ArrayList<Instance> trainingSet;
	private ArrayList<Instance> testSet;
	
	private double probSpam;
	private double probNotSpam;
	
	public NaiveBayes(ArrayList<Instance>training, ArrayList<Instance> test){
		trainingSet = training;
		testSet = test;
		
		//calculate p(spam) and P(not spam)
		probSpam = calculateProbabilitySpam(true);
		probNotSpam = calculateProbabilitySpam(false);
	}
	
	/**
	 * Returns the probability of an email being spam
	 * @param b true if spam, false if not spam
	 * @return probability
	 */
	public int calculateProbabilitySpam(boolean b){
		int count = 0;
		for(Instance i : trainingSet){
			if(i.getLabel() == 1){
				count++;
			}
		}
		
		//check whether we want to return p(spam) or p(not spam)
		if(b) return count/trainingSet.size();
		else return (trainingSet.size()-count)/trainingSet.size();
	}
	
	
}
