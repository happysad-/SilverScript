package silverscript;

import java.util.Scanner;

import silverscript.parser.ParseException;
import silverscript.parser.Parser;
import silverscript.parser.SemanticException;
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
			while(!line.contains("@exit"))
			{
				System.out.print(">> ");
				line = in.nextLine();
				
				if(line.contains("@exit"))
					break;
				else if(line.contains("@mmap")) {
					Parser.mmap();
					continue;
				}
				
				Interpreter.interpret(line);
			}
		}
		catch(ParseException | SemanticException |UnknownTokenException e)
		{
			e.printStackTrace();
		}
	}
}
