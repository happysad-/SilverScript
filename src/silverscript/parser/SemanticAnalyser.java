package silverscript.parser;

import silverscript.tokens.Token;
import silverscript.tokens.TokenType;

public class SemanticAnalyser
{
	@SuppressWarnings("unused")
	public static boolean isValidExpression(Token[] tokens, int offset, Token lastToken)
	{
		// If lastToken = null, offset should equal to 0
		if(lastToken == null)
		{
			if(tokens[offset].getType().equals(TokenType.CONSTANT))
			{
				if(tokens.length - 1 >= offset + 1)
					if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
						return true;
					else
						return false;
				else
					return true; // If only one thing.
			}
			else if(tokens[offset].getType().equals(TokenType.IDENTIFIER))
			{
				if(tokens.length - 1 >= offset + 1)
					if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
						return true;
					else
						return false;
				else
					return true; // If only one thing.
			}
			else if(tokens[offset].getType().equals(TokenType.NUMBER))
			{
				if(tokens.length - 1 >= offset + 1)
					if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
						return true;
					else
						return false;
				else
					return true; // If only one thing.
			}
			else if(tokens[offset].getType().equals(TokenType.OPERATOR))
			{
				if(tokens.length - 1 >= offset + 1)
					if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
						return true;
					else
						return false;
				else
					return true; // If only one thing.
			}
			else
				return false;
		}
		else if(lastToken != null)
		{
			if(!lastToken.getType().equals(TokenType.OPERATOR))
			{
				if(tokens[offset].getType().equals(TokenType.OPERATOR))
					if(tokens.length - 1 >= offset + 1)
						if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
							return true;
						else
							return false;
					else
						return false;
				return false;
			}
			else if(lastToken.getType().equals(TokenType.OPERATOR))
			{
				if(tokens[offset].getType().equals(TokenType.CONSTANT))
					if(tokens.length - 1 >= offset + 1)
						if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
							return true;
						else
							return false;
					else
						return true;
				else if(tokens[offset].getType().equals(TokenType.IDENTIFIER))
					if(tokens.length - 1 >= offset + 1)
						if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
							return true;
						else
							return false;
					else
						return true;
				else if(tokens[offset].getType().equals(TokenType.NUMBER))
					if(tokens.length - 1 >= offset + 1)
						if(isValidExpression(tokens, ++offset, tokens[offset - 1]))
							return true;
						else
							return false;
					else
						return true;
				else 
					return false;
			}
			else
				return false;
		}		
		
		return false;
	}
}
