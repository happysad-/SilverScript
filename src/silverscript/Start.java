package silverscript;

import java.util.List;
import java.util.Scanner;

import silverscript.parser.Parser;
import silverscript.parser.lexer.Lexer;
import silverscript.tokens.Token;
import silverscript.tokens.UnknownTokenException;

public class Start
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String line = "";
		
		try
		{
			while(!line.contains("exit"))
			{
				System.out.print(">> ");
				line = in.nextLine();
				if(line.contains("exit"))
					break;
				
				List<Token> tokens = Lexer.lex(line);
				
//				for(Token token : tokens)
//					token.output();
				
				Parser.semanticAnalysis(tokens);
				
//				List<Token> parseTree = ParseTree.parseExpression(tokens);
//				Evaluator.evalExpression(parseTree);
			}
			
//			ParseTree.traverse();
		}
		catch (UnknownTokenException e)
		{
			e.printStackTrace();
		}
	}
}
