
public class Instance {

	private int[] attributes;
	private int label;
	private int guess;
	
	public Instance(int[] attr, int label){
		attributes = attr;
		this.label = label;
	}
	
	public Instance(int[] attr){
		attributes = attr;
	}
	
	public int[] getAttr(){
		return attributes;
	}
	
	public int getLabel(){
		return label;
	}
	
	public void setGuess(int i){
		guess = i;
	}
	
	public int getGuess(){
		return guess;
	}
}
