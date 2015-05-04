package silverscript.evaluator;

import java.util.List;
import java.util.Map;

import silverscript.evaluator.functions.Function;
import silverscript.parser.Identifier;
import silverscript.parser.ParsedObject;
import silverscript.parser.Parser;
import silverscript.tokens.Token;
import silverscript.tokens.TokenType;

public class Evaluator
{
//	public static void evalExpression(List<Token> parseTree)
//	{
//		float left = 0;
//		float right = 0;
//		
//		boolean flag = false;
//		
//		for(Token token : parseTree)
//		{			
//			if(token.getType().equals(TokenType.NUMBER))
//			{
//				if(!flag)
//				{
//					left = Float.parseFloat(token.getValue().toString());
//					flag = true;
//				}
//				else
//				{
//					right = Float.parseFloat(token.getValue().toString());
//				}
//			}
//		}
//		
//		if(parseTree.get(0).getValue().equals("+"))
//			System.out.println(left + right);
//		else if(parseTree.get(0).getValue().equals("-"))
//			System.out.println(left - right);
//		else if(parseTree.get(0).getValue().equals("*"))
//			System.out.println(left * right);
//		else if(parseTree.get(0).getValue().equals("/"))
//			System.out.println(left / right);
//		else if(parseTree.get(0).getValue().equals("%"))
//			System.out.println(left % right);
//		else
//			System.err.println("Unknown expression!");
//	}
	
	private static float sum = 0;
	private static Identifier identifier = new Identifier("NULL_IDENTIFIER", 0);
	
	public static void evalExpression(Token[] tokens, int offset, Token lastToken)
	{
		if(lastToken == null)
		{
			if(!tokens[offset].getType().equals(TokenType.OPERATOR))
				sum = Float.parseFloat(tokens[offset].getValue().toString());
			else if(tokens[offset].getType().equals(TokenType.OPERATOR))
			{
				if(tokens[offset].getValue().equals("+"))
					sum = sum + Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("-"))
					sum = sum - Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("/"))
					sum = sum / Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("*"))
					sum = sum * Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("%"))
					sum = sum % Float.parseFloat(tokens[++offset].getValue().toString());
			}
			
			if(tokens.length - 1 > offset + 1)
				evalExpression(tokens, ++offset, tokens[offset - 1]);
			else
				System.out.println(sum);
			
			identifier.setValue(sum);
		}
		else if(lastToken != null)
		{
			if(!tokens[offset].getType().equals(TokenType.OPERATOR))
				sum = Float.parseFloat(tokens[offset].getValue().toString());
			else if(tokens[offset].getType().equals(TokenType.OPERATOR))
			{
				if(tokens[offset].getValue().equals("+"))
					sum = sum + Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("-"))
					sum = sum - Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("/"))
					sum = sum / Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("*"))
					sum = sum * Float.parseFloat(tokens[++offset].getValue().toString());
				else if(tokens[offset].getValue().equals("%"))
					sum = sum % Float.parseFloat(tokens[++offset].getValue().toString());
			}
			
			if(tokens.length - 1 > offset + 1)
				evalExpression(tokens, ++offset, tokens[offset - 1]);
			else
				System.out.println(sum);
			
			identifier.setValue(sum);
		}
	}
	
	public static void evalParsedObject(ParsedObject parsedObject)
	{
		/* Unpack parsed object */
		List<Token> tokenList = parsedObject.getTokenList();
		List<Function> functionList = parsedObject.getFunctionList();
		Map<String, Function> functionMap = parsedObject.getFunctionMap();
		Identifier identifier = parsedObject.getIdentifier();
		
		if(identifier != null)
			Evaluator.identifier = identifier;
		
		Token[] tokens = tokenList.toArray(new Token[tokenList.size()]);
		Function[] functions = functionList.toArray(new Function[functionList.size()]);
		
		/* Evaluate the function tokens into literals */
		float functionResult = 0F;
		
		for(int pointer = 0; pointer < tokens.length - 1; pointer++)
		{
			if(tokens[pointer].getType().equals(TokenType.FUNCTION)) // If token is function
			{
				/* Grab function from function map and call its method. */
				functionResult = functionMap.get(tokens[pointer].getValue().toString()).execute();
				tokens[pointer].setValue(functionResult);
			}
		}
		
		/* Evaluate the mathematical operations */
		if(tokens.length > 0)
			evalExpression(tokens, 0, null);
		
		Parser.addIdentifier(identifier);
		identifier = null;
	}
}
