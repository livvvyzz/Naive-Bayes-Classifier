
public class Instance {

	private int[] attributes;
	private int label;
	
	public Instance(int[] attr, int label){
		attributes = attr;
		label = label;
	}
	
	public int[] getAttr(){
		return attributes;
	}
	
	public int getLabel(){
		return label;
	}
}
