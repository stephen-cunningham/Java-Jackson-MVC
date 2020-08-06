package a2.model;

public class SubEvent extends Event  {
	
	//using a default constructor to ensure Jackson can work with the class
	public SubEvent(){
	}
	
	@Override
	public String toString() {
		String output;
		output = "\nThe event is " + this.getName() + " and it happened in " + this.getYear() + ".";
		//if the subevent has a type, this is added to the output
		if(this.getType() != null) {
			output += " It was a " + this.getType() + ".";
		}
		return output;
	}
}