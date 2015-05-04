package silverscript.parser;

import java.util.ArrayList;
import java.util.List;

import silverscript.tokens.Token;

public class ParsedObject
{
	private List<Token> tokenList;
	private List<Identifier> identifiers;
	
	public ParsedObject()
	{
		tokenList = new ArrayList<Token>();
		identifiers = new ArrayList<Identifier>();
	}
	
	public ParsedObject(List<Token> tokenList)
	{
		this.tokenList = tokenList;
		identifiers = new ArrayList<Identifier>();
	}
	
	public void combineParsedObjects(ParsedObject parsedObject)
	{
		tokenList.addAll(parsedObject.getTokenList());
		identifiers.addAll(parsedObject.getIdentifierList());
	}
	
	public List<Token> getTokenList()
	{
		return tokenList;
	}
	
	public List<Identifier> getIdentifierList()
	{
		return identifiers;
	}
	
	public void addIdentifier(Identifier identifier)
	{
		identifiers.add(identifier);
	}
	
	public void addToken(Token token)
	{
		tokenList.add(token);
	}
}
