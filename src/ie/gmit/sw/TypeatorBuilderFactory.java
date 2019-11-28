package ie.gmit.sw;

public class TypeatorBuilderFactory extends TypeatorBuilderImpl implements Typeator {

	private static TypeatorBuilderFactory tbf = new TypeatorBuilderFactory();

	private TypeatorBuilderFactory() {
	}
	
	public static TypeatorBuilderFactory getInstance()
	{
		return tbf;
	}
	
	TypeatorBuilder getTypeatorBuilder() {
		
		return null;
	}
	
	public Typeator newTypeator()
	{
		return new TypeatorBuilderFactory();
	}

	@Override
	public int execute() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int invoke() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	   
}
