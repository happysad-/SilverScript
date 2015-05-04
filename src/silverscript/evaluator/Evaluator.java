package silverscript.evaluator;

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
		}
	}
}
