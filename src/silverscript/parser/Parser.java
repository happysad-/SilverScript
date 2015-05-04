package silverscript.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import silverscript.parser.lexer.Lexer;
import silverscript.tokens.Token;
import silverscript.tokens.TokenType;

public class Parser
{
	private static Map<String, Object> identifierMap = new HashMap<String, Object>();
	
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
		
		if(lastToken == null)
		{
			if(tokens[offset].getType().equals(TokenType.IDENTIFIER))
			{
				int identifierPointer = offset;
				int identifierValue = offset + 2;

				identifierMap.put(tokens[identifierPointer].getValue().toString(), tokens[identifierValue].getValue());
				parsedObject.addIdentifier(new Identifier(tokens[identifierPointer].getValue().toString(), tokens[identifierValue].getValue()));
				
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
