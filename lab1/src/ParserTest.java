import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parseExpression() {
        try {
            StringReader reader = new StringReader("5+(60*4/5)*2^3");
            Lexer lexer = new Lexer(reader);
            Parser parse = new Parser(lexer);
            int expected = parse.ParseExpression();
            int actual = 389;
            Assert.assertEquals(expected, actual);
        } catch (IOException | LexerException | ParserException e) {
            e.printStackTrace();
        }
    }
}