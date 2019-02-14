import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            StringReader reader = new StringReader("(2+3");
            Lexer lexer = new Lexer(reader);
            Parser parse = new Parser(lexer);
            System.out.println(parse.parse());
        } catch (IOException | LexerException | ParserException e) {
            e.printStackTrace();
        }
    }
}