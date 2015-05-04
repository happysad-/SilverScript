package silverscript.parser.lexer;

import java.util.ArrayList;
import java.util.List;

import silverscript.tokens.Token;
import silverscript.tokens.TokenType;
import silverscript.tokens.UnknownTokenException;

public class Lexer
{
	private static String[] keywords = {
		"byte", "int", "str", "ptr", "boolean", "void",
		"using", "private", "public", "null",
		"for", "while", "new", "return"
	};
	
	public static List<Token> lex(String input) throws UnknownTokenException
	{
		List<Token> tokenList = new ArrayList<Token>();
		String[] strings = input.split(" "); /* Split at spaces */
		
		for(int i = 0; i < strings.length; i++)
		{
			String current = strings[i];
			
			if(isKeyword(current))
				tokenList.add(new Token(current, TokenType.KEYWORD));
			else if(isConstant(current))
				tokenList.add(new Token(current, TokenType.CONSTANT));
			else if(isIdentifier(current))
				tokenList.add(new Token(current, TokenType.IDENTIFIER));
//			else if(isDigit(current))
//				tokenList.add(new Token(current, TokenType.DIGIT));
			else if(isNumber(current))
				tokenList.add(new Token(current, TokenType.NUMBER));
			else if(isOperator(current))
				tokenList.add(new Token(current, TokenType.OPERATOR));
			else if(isBitwiseOperator(current))
				tokenList.add(new Token(current, TokenType.BITOPERATOR));
			else if(isString(current))
				tokenList.add(new Token(current, TokenType.STRING));
			else if(isDelimiter(current))
				tokenList.add(new Token(current, TokenType.DELIMITER));
			else if(isFunction(current))
				tokenList.add(new Token(current, TokenType.FUNCTION));
			else throw new UnknownTokenException("Unknown token type!");
		}
		
		return tokenList;
	}
	
	private static boolean isNumber(String string)
	{
		if(string.matches("(-)?[0-9]+")) /* Regex matches number with or without - sign. */
			return true;
		else if(string.matches("0x[0-9a-fA-F]+")) /* Regex matches hex number format. */
			return true;
		else if(string.matches("[0-9]+\\.[0-9]+")) /* Regex matches decimals. */
			return true;
		return false;
	}
	
	private static boolean isOperator(String string)
	{
		if(string.matches("(\\+|-|\\/|\\*|%|=|>|<|(<=)|(>=))?")) /* Regex matches operator. */
			return true;
		return false;
	}
	
	private static boolean isBitwiseOperator(String string)
	{
		if(string.matches("(&|~|\\||\\^|>>|<<)?")) /* Regex matches bitwise operators. */
			return true;
		return false;
	}
	
	private static boolean isKeyword(String string)
	{
		for(String keyword : keywords)
			if(string.equals(keyword))
				return true;
		return false;
	}
	
//	private static boolean isDigit(String string)
//	{
//		if(string.matches("[0-9]")) /* Regex matches a single digit. */
//			return true;
//		else if(string.matches("0x(0)*[0-9]")) /* Regex matches a single digit in hex. */
//			return true;
//		return false;
//	}
	
	private static boolean isString(String string)
	{
		if(string.startsWith("\""))
			return true;
		return false;
	}
	
	private static boolean isConstant(String string)
	{
		if(string.equals("const"))
			return true;
		return false;
	}
	
	private static boolean isIdentifier(String string)
	{
		if(string.matches("[a-zA-Z]+[0-9a-zA-Z]*")) /* Regex matches anything starting with a letter. */
			return true;
		return false;
	}
	
	private static boolean isDelimiter(String string)
	{
		if(string.matches("\\(([a-zA-Z0-9])*\\)|\\(([a-zA-Z0-9])*\\s([a-zA-Z0-9])*\\)")) /* Regex matches any delimiters "(...)". */
			return true;
		return false;
	}
	
	public static boolean isFunction(String string)
	{
		if(string.startsWith("@"))
			return true;
		return false;
	}
	
	public static boolean isFunction(List<Token> tokenList)
	{
		for(Token token : tokenList)
			if(token.getValue().toString().startsWith("@"))
				return true;
		return false;
	}
	
	public static boolean isExpression(List<Token> tokenList)
	{
		for(Token token : tokenList)
			if(!isIdentifier(token.getValue().toString()) || !isString(token.getValue().toString()) || !isKeyword(token.getValue().toString()))
				return true;
		return false;
	}
	
	public static boolean isEquation(List<Token> tokenList)
	{
		for(Token token : tokenList)
			if(token.getValue().toString().contains("="))
				return true;
		return false;
	}
}
