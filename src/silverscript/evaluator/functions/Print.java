package silverscript.evaluator.functions;

import silverscript.tokens.Token;

public class Print extends Function
{
	public Print()
	{
		super("@println");
	}

	@Override
	public void executeVoid(Token... tokens)
	{
		if(tokens != null)
			System.out.println(tokens[0].getValue().toString());
		else
			System.out.println();
	}
}
