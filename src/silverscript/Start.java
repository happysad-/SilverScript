package silverscript;

import java.util.List;
import java.util.Scanner;

import silverscript.parser.Identifier;
import silverscript.parser.ParseException;
import silverscript.parser.ParsedObject;
import silverscript.parser.Parser;
import silverscript.parser.SemanticException;
import silverscript.parser.lexer.Lexer;
import silverscript.tokens.Token;
import silverscript.tokens.UnknownTokenException;

public class Start
{
	/*
	 * Process:
	 * 	Read in the line.
	 * 	Lex it into tokens.
	 * 	Analyse semantically.
	 * 	If semantics true:
	 * 		Create a parse tree ? list.
	 * 		Does tree ? list use identifiers:
	 * 			Check if identifier is not null.
	 * 				Evaluate the parse tree ? list.
	 * 			Else
	 * 				Throw null error.
	 * 		Else
	 * 			Evaluate the parse tree ? list.
	 * 	Else
	 * 		Throw a semantic error.
	 */
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
				
				ParsedObject parsedObject = null;
				
				if(Parser.semanticAnalysis(tokens))
				{
					System.out.println("Passed.");
					parsedObject = Parser.parseTokenList(tokens.toArray(new Token[tokens.size()]), 0, null);
				
					for(Identifier identifier : parsedObject.getIdentifierList())
						System.out.println("I[" + identifier.getIdentifier() + ", " + identifier.getValue() + "]");
					for(Token token : parsedObject.getTokenList())
						System.out.println("T[" + token.getType().toString() + ", " + token.getValue() + "]");
				}
//					Evaluator.evalExpression(tokens.toArray(new Token[tokens.size()]), 0, null);
				
//				List<Token> parseTree = ParseTree.parseExpression(tokens);
//				Evaluator.evalExpression(parseTree);
			}
			
//			ParseTree.traverse();
		}
		catch (UnknownTokenException e)
		{
			e.printStackTrace();
		}
		catch (SemanticException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}
}
