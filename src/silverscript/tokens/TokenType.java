package silverscript.tokens;

public enum TokenType
{
	/*
	 * Keyword 		: while
	 * Constant 	: const i = 5
	 * Identifier 	: int x (x being identifier)
	 * String		: "this is a string"
	 * Operator		: +
	 * Bitoperator	: |
	 * Number		: 1024
	 * Delimiter	: ()
	 * Expression	: <identifier>|<operator>|<number>|<constant>
	 * Equation		: <identifier> = <expression>*
	 */
	KEYWORD, CONSTANT, IDENTIFIER, STRING, OPERATOR, BITOPERATOR, NUMBER, /*DIGIT,*/ DELIMITER;
}
