import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parse() {
        try {
            StringReader reader = new StringReader("5+ (60*4/5)*2^3");
            Lexer lexer = new Lexer(reader);
            Parser parse = new Parser(lexer);
            int actual = parse.parse();
            int expected = 389;
            Assert.assertEquals(expected, actual);
        } catch (IOException | LexerException | ParserException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void ParseWithWrongExprestion() throws Exception {
        expected.expect(ParserException.class);
        expected.expectMessage("wrong expression");
        StringReader reader = new StringReader("((3+45*7653)^4))");
        Lexer lexer = new Lexer(reader);
        Parser parse = new Parser(lexer);
        parse.parse();
    }

    @Test
    public void ParseWithDifferentNumberOfBraces() throws Exception{
        expected.expect(ParserException.class);
        expected.expectMessage("Different number of opening and closing brackets");
        StringReader reader = new StringReader("((3+45*7653)^4");
        Lexer lexer = new Lexer(reader);
        Parser parse = new Parser(lexer);
        parse.parse();
    }
}