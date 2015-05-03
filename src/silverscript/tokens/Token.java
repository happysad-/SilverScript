package silverscript.tokens;

public class Token
{
	private TokenType type;
	private Object value;
	
	public Token()
	{
		this.value = null;
		this.type = null;
	}
	
	public Token(TokenType type)
	{
		this.value = null;
		this.type = type;
	}
	
	public Token(Object value, TokenType type)
	{
		this.value = value;
		this.type = type;
	}
	
	public void setType(TokenType type)
	{
		this.type = type;
	}
	
	public void setValue(Object value)
	{
		this.value = value;
	}
	
	public TokenType getType()
	{
		return type;
	}
	
	public Object getValue()
	{
		return value;
	}
	
	public void output()
	{
		System.out.println("[\"" + type.name() + "\", " + value + "]");
	}
}
