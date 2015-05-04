package silverscript.parser;


public class Identifier
{
	private String identifier;
	private Object value;
	
	public Identifier()
	{
		identifier = null;
		value = null;
	}
	
	public Identifier(String identifier)
	{
		this.identifier = identifier;
		value = null;
	}
	
	public Identifier(String identifier, Object value)
	{
		this.identifier = identifier;
		this.value = value;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public Object getValue()
	{
		return value;
	}
	
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
	
	public void setValue(Object value)
	{
		this.value  = value;
	}
}
