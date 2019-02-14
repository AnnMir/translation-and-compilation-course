import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    int current;
    private StringBuilder number;

    Lexer(Reader reader) throws IOException {
        this.reader = reader;
        this.current = reader.read();
        number = new StringBuilder();
    }

    Lexeme getLexeme() throws IOException, LexerException {
        while (current == 32) {
            current = reader.read();
        }
        switch (current) {
            case 43:
                current = reader.read();
                return new Lexeme(LexemeType.PLUS, "+");
            case 45:
                current = reader.read();
                return new Lexeme(LexemeType.MINUS, "-");
            case 42:
                current = reader.read();
                return new Lexeme(LexemeType.MULTIPLICATION, "*");
            case 47:
                current = reader.read();
                return new Lexeme(LexemeType.DIVISION, "/");
            case 94:
                current = reader.read();
                return new Lexeme(LexemeType.POWER, "^");
            case 40:
                current = reader.read();
                return new Lexeme(LexemeType.OPENING_BRACKET, "(");
            case 41:
                current = reader.read();
                return new Lexeme(LexemeType.CLOSING_BRACKET, ")");
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return Number();
            case -1:
                return new Lexeme(LexemeType.EOF, "-1");
            default:
                throw new LexerException("Unknown character");
        }
    }

    private Lexeme Number() throws IOException {
        while (current >= 48 && current <= 57) {
            switch (current) {
                case 48:
                    number.append("0");
                    break;
                case 49:
                    number.append("1");
                    break;
                case 50:
                    number.append("2");
                    break;
                case 51:
                    number.append("3");
                    break;
                case 52:
                    number.append("4");
                    break;
                case 53:
                    number.append("5");
                    break;
                case 54:
                    number.append("6");
                    break;
                case 55:
                    number.append("7");
                    break;
                case 56:
                    number.append("8");
                    break;
                case 57:
                    number.append("9");
                    break;
                default:
                    break;

            }
            current = reader.read();
        }
        String str = number.toString();
        number.delete(0,number.length());
        return new Lexeme(LexemeType.NUMBER, str);
    }
}
