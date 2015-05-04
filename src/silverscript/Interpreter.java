package silverscript;

import java.util.List;

import silverscript.evaluator.Evaluator;
import silverscript.evaluator.functions.Function;
import silverscript.parser.ParseException;
import silverscript.parser.ParsedObject;
import silverscript.parser.Parser;
import silverscript.parser.SemanticException;
import silverscript.parser.lexer.Lexer;
import silverscript.tokens.Token;
import silverscript.tokens.UnknownTokenException;

public class Interpreter
{
	public static void interpret(String line) throws ParseException, SemanticException, UnknownTokenException
	{
		List<Token> tokens = Lexer.lex(line);
			
		ParsedObject parsedObject = null;
			
		if(Parser.semanticAnalysis(tokens))
		{
			System.out.println("Passed.");
			parsedObject = Parser.parseTokenList(tokens.toArray(new Token[tokens.size()]), 0, null);
			
//			Identifier identifier = parsedObject.getIdentifier();
//			System.out.println("I[" + identifier.getIdentifier() + ", " + identifier.getValue() + "]");
			
			for(Token token : parsedObject.getTokenList())
				System.out.println("T[" + token.getType().toString() + ", " + token.getValue() + "]");
			for(Function function : parsedObject.getFunctionList())
				System.out.println("F[" + function.getFunctionID() + "]");
			
			Evaluator.evalParsedObject(parsedObject);
		}
	}
}
