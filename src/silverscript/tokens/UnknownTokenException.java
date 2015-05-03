package silverscript.tokens;

public class UnknownTokenException extends Exception
{
	private static final long serialVersionUID = -1734412089820258615L;
	
	public UnknownTokenException(String message)
	{
		super(message);
	}
	
	@Override
	public void printStackTrace()
	{
		System.err.println(this.getMessage());
	}
}
