package silverscript.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import silverscript.evaluator.Evaluator;
import silverscript.tokens.Token;
import silverscript.tokens.TokenType;

public class Parser
{
	private static Map<String, Object> identifierMap = new HashMap<String, Object>();
	
	public static boolean semanticAnalysis(List<Token> tokenList)
	{
		Token[] tokens = tokenList.toArray(new Token[tokenList.size()]);
		
		// Check if is an expression.
		// Check if it is a valid expression.
		
		// Check if is an equation.
		// Check if it is a valid equation.
				
		boolean test = SemanticAnalyser.isValidExpression(tokens, 0, null);
		
//		System.out.println("Passed? " + test);
		
//		for(Token token : parseTokenList(tokens, 0, null))
//			token.output();
		
		if(test)
			Evaluator.eval(tokens, 0, null);
		
		return false;
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
	
	public static List<Token> parseTokenList(Token[] tokens, int offset, Token lastToken)
	{
		List<Token> parsedList = new ArrayList<Token>();
		
		if(lastToken == null)
		{
			if(tokens[offset].getType().equals(TokenType.IDENTIFIER))
			{
				int identifierPointer = offset;
				int identifierValue = offset + 2;
				identifierMap.put(tokens[identifierPointer].getValue().toString(), tokens[identifierValue].getValue());
				
				parsedList.add(tokens[offset]);
				++offset;
				parsedList.add(tokens[offset]);
				++offset;
				parsedList.add(tokens[offset]);
			}
		}
		
		return parsedList;
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
