package silverscript.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import silverscript.evaluator.functions.Function;
import silverscript.tokens.Token;

public class ParsedObject
{
	private List<Token> tokenList;
	private Identifier identifier;
	private List<Function> functionList;
	private Map<String, Function> functionMap;
	
	public ParsedObject()
	{
		tokenList = new ArrayList<Token>();
		identifier = null;
		functionList = new ArrayList<Function>();
		functionMap = new HashMap<String, Function>();
	}
	
	public ParsedObject(List<Token> tokenList)
	{
		this.tokenList = tokenList;
		identifier = null;		
		functionList = new ArrayList<Function>();
		functionMap = new HashMap<String, Function>();
	}
	
	public void combineParsedObjects(ParsedObject parsedObject)
	{
		tokenList.addAll(parsedObject.getTokenList());
		identifier = parsedObject.getIdentifier();
		functionList.addAll(parsedObject.getFunctionList());
		functionMap.putAll(parsedObject.getFunctionMap());
	}
	
	public Map<String, Function> getFunctionMap()
	{
		return functionMap;
	}
	
	public List<Function> getFunctionList()
	{
		return functionList;
	}
	
	public List<Token> getTokenList()
	{
		return tokenList;
	}
	
	public Identifier getIdentifier()
	{
		return identifier;
	}
	
	public void addFunctionToMap(Function function)
	{
		functionMap.put(function.getFunctionID(), function);
	}
	
	public void addFunction(Function function)
	{
		functionList.add(function);
	}
	
	public void addIdentifier(Identifier identifier)
	{
		this.identifier = identifier;
	}
	
	public void addToken(Token token)
	{
		tokenList.add(token);
	}
}
