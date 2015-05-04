package silverscript.evaluator.functions;

import silverscript.tokens.Token;

public class Function
{
	private String id;
	private Token[] tokens;
	
	public Function(String id)
	{
		this.id = id;
	}
	
	public Function(String id, Token[] tokens)
	{
		this.id = id;
		this.tokens = tokens;
	}
	
	public String getFunctionID() { return id; }
	
	public Token[] getTokens() { return tokens; }
	
	public Function initFunction(Token[] tokens) { this.tokens = tokens; return this; }
	
	public void executeVoid(Token... tokens) { this.tokens = tokens; }
	
	public float executeFloat(Token... tokens) { this.tokens = tokens; return 0F; }
}
