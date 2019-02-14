import java.io.IOException;

public class Parser {

    private Lexer lexer;
    private Lexeme current;
    private int open_braces;
    private int close_braces;

    Parser(Lexer lexer) throws IOException, LexerException {
        this.lexer = lexer;
        current = lexer.getLexeme();
    }

    int parse() throws ParserException, IOException, LexerException {
        int result = ParseExpression();
        if(current.type != LexemeType.EOF){
            throw new ParserException("wrong expression");
        }
        return result;
    }

    int ParseExpression() throws IOException, LexerException, ParserException {
        int temp = ParseTerm();
        while (current.type == LexemeType.PLUS || current.type == LexemeType.MINUS) {
            if (current.type == LexemeType.PLUS) {
                current = lexer.getLexeme();
                temp += ParseTerm();
            }
            if (current.type == LexemeType.MINUS) {
                current = lexer.getLexeme();
                temp -= ParseTerm();
            }
        }
        return temp;
    }

    private int ParseTerm() throws IOException, LexerException, ParserException {
        int temp = ParseFactor();
        while (current.type == LexemeType.MULTIPLICATION || current.type == LexemeType.DIVISION) {
            if (current.type == LexemeType.MULTIPLICATION) {
                current = lexer.getLexeme();
                temp *= ParseFactor();
            }
            if (current.type == LexemeType.DIVISION) {
                current = lexer.getLexeme();
                temp /= ParseFactor();
            }
        }
        return temp;
    }

    private int ParseFactor() throws IOException, LexerException, ParserException {
        int temp = ParsePower();
        if (current.type == LexemeType.POWER) {
            current = lexer.getLexeme();
            temp = (int) Math.pow(temp, ParseFactor());
        }
        return temp;
    }

    private int ParsePower() throws IOException, LexerException, ParserException {
        if(current.type == LexemeType.MINUS){
            current = lexer.getLexeme();
            return -ParseAtom();
        }
        return ParseAtom();
    }

    private int ParseAtom() throws IOException, LexerException, ParserException {
        if(current.type == LexemeType.NUMBER){
            int temp = Integer.parseInt(current.text);
            current = lexer.getLexeme();
            return temp;
        }
        if(current.type == LexemeType.OPENING_BRACKET){
            open_braces++;
            current = lexer.getLexeme();
            int temp = ParseExpression();
            if(current.type != LexemeType.CLOSING_BRACKET)
                throw new ParserException("Different number of opening and closing brackets");
            close_braces++;
            current = lexer.getLexeme();
            return temp;
        }
        if(current.type == LexemeType.CLOSING_BRACKET)
            close_braces++;
            if(open_braces<close_braces)
            throw  new ParserException("Number of closing braces is greater than opening");
        else
            close_braces++;
        throw new ParserException("wrong character");
    }
}



