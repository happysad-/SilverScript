package silverscript.evaluator.functions;


public class Print extends Function
{
	public Print()
	{
		super("@println");
	}

	@Override
	public float execute()
	{
		if(tokens != null)
			System.out.println(tokens[0].getValue().toString());
		else
			System.out.println();
		return 0F;
	}
}
