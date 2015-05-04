package silverscript.parser;

import java.util.ArrayList;
import java.util.List;

import silverscript.evaluator.functions.Function;
import silverscript.tokens.Token;

public class ParsedObject
{
	private List<Token> tokenList;
	private List<Identifier> identifiers;
	private List<Function> functionList;
	
	public ParsedObject()
	{
		tokenList = new ArrayList<Token>();
		identifiers = new ArrayList<Identifier>();
		functionList = new ArrayList<Function>();
	}
	
	public ParsedObject(List<Token> tokenList)
	{
		this.tokenList = tokenList;
		identifiers = new ArrayList<Identifier>();
		functionList = new ArrayList<Function>();
	}
	
	public void combineParsedObjects(ParsedObject parsedObject)
	{
		tokenList.addAll(parsedObject.getTokenList());
		identifiers.addAll(parsedObject.getIdentifierList());
		functionList.addAll(parsedObject.getFunctionList());
	}
	
	public List<Function> getFunctionList()
	{
		return functionList;
	}
	
	public List<Token> getTokenList()
	{
		return tokenList;
	}
	
	public List<Identifier> getIdentifierList()
	{
		return identifiers;
	}
	
	public void addFunction(Function function)
	{
		functionList.add(function);
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
