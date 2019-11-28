package ie.gmit.sw;

public interface TypeatorBuilder {

	public default void initialise(int max_size) throws Exception {;
		if(max_size > Typeator.MAX || max_size <0) {
			throw new Exception();
		}
	}
	
	abstract Typeator[] construct(TypeatorType e);
	
}
