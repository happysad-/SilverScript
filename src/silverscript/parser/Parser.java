package silverscript.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import silverscript.evaluator.functions.Function;
import silverscript.evaluator.functions.Print;
import silverscript.parser.lexer.Lexer;
import silverscript.tokens.Token;
import silverscript.tokens.TokenType;

public class Parser
{
	private static Map<String, Object> identifierMap = new HashMap<String, Object>();
	private static Map<String, Function> functionMap = new HashMap<String, Function>();
	
	public static void registerFunctions()
	{
		Function print = new Print();
		
		functionMap.put(print.getFunctionID(), print);
	}
	
	public static void mmap()
	{
		Iterator<Entry<String, Object>> i = identifierMap.entrySet().iterator();
		
		while(i.hasNext())
		{
			Map.Entry<String, Object> pair = (Map.Entry<String, Object>)i.next();
			
			System.out.println("MEM: [" + pair.getKey() + ", " + pair.getValue() + "]");
		}
	}
	
	public static boolean semanticAnalysis(List<Token> tokenList) throws SemanticException
	{
		Token[] tokens = tokenList.toArray(new Token[tokenList.size()]);
		
		// Check if is an expression.
		// Check if it is a valid expression.
		
		// Check if is an equation.
		// Check if it is a valid equation.
		
		if(Lexer.isEquation(tokenList))
			if(SemanticAnalyser.isValidEquation(tokens))
				return true;	/* True if is a valid equation. */
			else
				return false;	/* False if is an invalid equation. */
		else if(Lexer.isExpression(tokenList))
			if(SemanticAnalyser.isValidExpression(tokens, 0, null))
				return true;	/* True if is a valid expression. */
			else
				return false;	/* False if is an invalid expression. */
		else if(Lexer.isFunction(tokenList))
			return true;
		else
			return false;		/* False if does not fit criteria. */
	}
	
	/*
	 * Parsing:
	 * 
	 * 		is an equation:
	 * 			get the identifiers pointer.
	 * 			make it root of parse tree.
	 * 			from root, on the left all numbers.
	 * 			from root, on the right all operators.
	 * 			return to evaluate.
	 */
	
	public static ParsedObject parseTokenList(Token[] tokens, int offset, Token lastToken) throws ParseException
	{
		ParsedObject parsedObject = new ParsedObject();
		
		if(tokens[offset].getType().equals(TokenType.FUNCTION))
		{
			parsedObject.combineParsedObjects(parseFunction(tokens));
		}
		
		if(lastToken == null)
		{
			if(tokens[offset].getType().equals(TokenType.IDENTIFIER))
			{
				int identifierPointer = offset;
				int identifierValue = offset + 2;

				if(tokens[identifierValue].getType().equals(TokenType.NUMBER))
					identifierMap.put(tokens[identifierPointer].getValue().toString(), tokens[identifierValue].getValue());
				else if(tokens[identifierValue].getType().equals(TokenType.IDENTIFIER))
					identifierMap.put(tokens[identifierPointer].getValue().toString(), identifierMap.get(tokens[identifierValue].getValue().toString()));
				
				if(!identifierMap.containsKey(tokens[offset]))
					parsedObject.addIdentifier(new Identifier(tokens[identifierPointer].getValue().toString(), tokens[identifierValue].getValue()));
				else
					parsedObject.addToken(new Token(identifierMap.get(tokens[identifierValue].getValue().toString()), TokenType.NUMBER));
				
				++offset;
				++offset;
				
				if(tokens.length - 1 >= offset + 1)
					parsedObject.combineParsedObjects(parseTokenList(tokens, ++offset, tokens[offset - 1]));
				else 
					return parsedObject;
			}
			else if(tokens[offset].getType().equals(TokenType.KEYWORD))
			{
				/*
				 * Check which keyword, then generate a parsedObject for every iteration
				 * if it is a loop.
				 * Else do what the keyword specifies.
				 */
			}
			else
				throw new ParseException("Unable to create a parse object.");
		}
		else if(lastToken != null)
		{
			if(lastToken.getType().equals(TokenType.IDENTIFIER) || lastToken.getType().equals(TokenType.NUMBER))
			{
				if(tokens[offset].getType().equals(TokenType.OPERATOR))
				{
					++offset; //Increment offset
					
					if(tokens[offset].getType().equals(TokenType.IDENTIFIER))
					{
						parsedObject.addToken(tokens[offset - 1]); 	// Add operator
//						parsedObject.addToken(tokens[offset]);		// Add identifier
						parsedObject.addToken(new Token(identifierMap.get(tokens[offset].getValue().toString()), TokenType.NUMBER));	// Substitute identifier for a literal.
						
						if(tokens.length - 1 >= offset + 1)
							parsedObject.combineParsedObjects(parseTokenList(tokens, ++offset, tokens[offset - 1]));
						else
							return parsedObject;
					}
					else if(tokens[offset].getType().equals(TokenType.CONSTANT))
					{
						parsedObject.addToken(tokens[offset - 1]); 	// Add operator
						parsedObject.addToken(tokens[offset]);		// Add constant
						
						if(tokens.length - 1 >= offset + 1)
							parsedObject.combineParsedObjects(parseTokenList(tokens, ++offset, tokens[offset - 1]));
						else
							return parsedObject;
					}
					else if(tokens[offset].getType().equals(TokenType.NUMBER))
					{
						parsedObject.addToken(tokens[offset - 1]); 	// Add operator
						parsedObject.addToken(tokens[offset]);		// Add number
						
						if(tokens.length - 1 >= offset + 1)
							parsedObject.combineParsedObjects(parseTokenList(tokens, ++offset, tokens[offset - 1]));
						else
							return parsedObject;
					}
				}
				else //if not followed by an operator
					throw new ParseException("Unable to create a parse object.");
			}
			else if(lastToken.getType().equals(TokenType.OPERATOR))
			{
				if(tokens[offset].getType().equals(TokenType.IDENTIFIER))
				{
					parsedObject.addToken(lastToken);	// Add identifier.
					parsedObject.addToken(new Token(tokens[offset + 2].getValue(), tokens[offset + 2].getType()));	// Substitute identifier for a literal.
				
					if(tokens.length - 1 >= offset + 1)
						parsedObject.combineParsedObjects(parseTokenList(tokens, ++offset, tokens[offset - 1]));
					else
						return parsedObject;
				}
				else if(tokens[offset].getType().equals(TokenType.CONSTANT))
				{
					parsedObject.addToken(lastToken);
					parsedObject.addToken(tokens[offset]);
					
					if(tokens.length - 1 >= offset + 1)
						parsedObject.combineParsedObjects(parseTokenList(tokens, ++offset, tokens[offset - 1]));
					else
						return parsedObject;
				}
				else if(tokens[offset].getType().equals(TokenType.NUMBER))
				{
					parsedObject.addToken(lastToken);
					parsedObject.addToken(tokens[offset]);
					
					if(tokens.length - 1 >= offset + 1)
						parsedObject.combineParsedObjects(parseTokenList(tokens, ++offset, tokens[offset - 1]));
					else
						return parsedObject;
				}
				else 
					throw new ParseException("Unable to create a parse object.");
			}
			else
				throw new ParseException("Unable to create a parse object.");
		}
		
		return parsedObject;
	}
	
	public static ParsedObject parseFunction(Token[] tokens) throws ParseException
	{
		ParsedObject parsedObject = new ParsedObject();
		int offset = 0;
		
		if(Lexer.isFunction(tokens[offset].getValue().toString()))
		{
			String functionID = tokens[0].getValue().toString();
			List<Token> reqTokens = new ArrayList<Token>();
			Token tmp = new Token();
			
			if(tokens[++offset].getValue().equals("("))
			{
				while(!tmp.getValue().equals(")"))
				{
					++offset;
					
					tmp = tokens[offset];
					
					if(!tokens[offset].getType().equals(TokenType.DELIMITER) || !tokens[offset].getType().equals(TokenType.KEYWORD))
					{
						reqTokens.add(tokens[offset]);
						if(tokens.length - 1 >= offset + 1)
							if(tokens[offset + 1].getType().equals(TokenType.DELIMITER))
								continue;
							else
								throw new ParseException("Could not create a parsed object.");
						else
							parsedObject.addFunction(functionMap.get(functionID).initFunction(reqTokens.toArray(new Token[reqTokens.size()])));
					}
				}
			}
		}
		else
			return parsedObject;
		
		return parsedObject;
	}
	
	@SuppressWarnings("unused")
	private static Token getOperator(List<Token> tokenList)
	{
		Token operator = null;
		
		for(Token token : tokenList)
		{
			if(token.getType().equals(TokenType.OPERATOR))
				return token;
		}
		
		return operator;
	}
	
//	@SuppressWarnings("unused")
//	private static Token getNumber(List<Token> tokenList) throws ParseException
//	{
//		boolean opFound = false;
//		
//		for(Token token : tokenList)
//		{
//			if(!opFound)
//			{
//				if(token.getType().equals(TokenType.OPERATOR))
//					continue;
//			}
//			else
//				throw new ParseException("Operator was not found.");
//			
//			if(token.getType().equals(TokenType.NUMBER))
//				return token;
//		}
//		
//		return null;
//	}
}
