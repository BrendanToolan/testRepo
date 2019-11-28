package ie.gmit.sw;

public interface Typeator {

	static int MAX = 7;
	
	abstract int execute();
	
	public default int invoke()
	{
		return init(MAX);
	}
	
	private int init(int value) {
		return (value + 1);
	}
	
}
