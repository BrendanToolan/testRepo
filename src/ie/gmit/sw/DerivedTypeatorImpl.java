package ie.gmit.sw;

public class DerivedTypeatorImpl implements DerivedTypeator{

	public DerivedTypeatorImpl(Typeator t) {
	}
	
	public int execute() {
		return 0;
		
	}
	
	public int invoke() {
		return 0;
		
	}
	
	public int call() {
		return execute() + 1;
	}
}
