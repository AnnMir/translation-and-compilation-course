import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void getLexeme() {
        try {
            StringReader reader = new StringReader("5+-4()*5676/^");
            Lexer lexer = new Lexer(reader);
            Lexeme lexeme1 = new Lexeme(LexemeType.NUMBER,"5");
            Lexeme lexeme2 = new Lexeme(LexemeType.PLUS, "+");
            Lexeme lexeme3 = new Lexeme(LexemeType.MINUS, "-");
            Lexeme lexeme4 = new Lexeme(LexemeType.NUMBER,"4");
            Lexeme lexeme5 = new Lexeme(LexemeType.OPENING_BRACKET,"(");
            Lexeme lexeme6 = new Lexeme(LexemeType.CLOSING_BRACKET, ")");
            Lexeme lexeme7 = new Lexeme(LexemeType.MULTIPLICATION, "*");
            Lexeme lexeme8 = new Lexeme(LexemeType.NUMBER,"5676");
            Lexeme lexeme9 = new Lexeme(LexemeType.DIVISION,"/");
            Lexeme lexeme10 = new Lexeme(LexemeType.POWER, "^");
            List<String> actual = new ArrayList<>();
            while(lexer.current != -1){
                Lexeme lex = lexer.getLexeme();
                actual.add(lex.text);
            }
            List<String> expected = new ArrayList<>();
            expected.add(lexeme1.text);
            expected.add(lexeme2.text);
            expected.add(lexeme3.text);
            expected.add(lexeme4.text);
            expected.add(lexeme5.text);
            expected.add(lexeme6.text);
            expected.add(lexeme7.text);
            expected.add(lexeme8.text);
            expected.add(lexeme9.text);
            expected.add(lexeme10.text);

            Assert.assertEquals(expected, actual);

        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getLexemeSpaces(){
        try {
            StringReader reader = new StringReader("5+   4");
            Lexer lexer = new Lexer(reader);
            Lexeme lexeme1 = new Lexeme(LexemeType.NUMBER,"5");
            Lexeme lexeme2 = new Lexeme(LexemeType.PLUS, "+");
            Lexeme lexeme3 = new Lexeme(LexemeType.NUMBER,"4");

            List<String> actual = new ArrayList<>();
            while(lexer.current != -1){
                Lexeme lex = lexer.getLexeme();
                actual.add(lex.text);
            }
            List<String> expected = new ArrayList<>();
            expected.add(lexeme1.text);
            expected.add(lexeme2.text);
            expected.add(lexeme3.text);
            Assert.assertEquals(expected, actual);

        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLexemeWithWrongCharacter(){
        Throwable caught = null;
        try{
            StringReader reader = new StringReader("+g");
            Lexer lexer = new Lexer(reader);
            List<String> actual = new ArrayList<>();
            while(lexer.current != -1){
                Lexeme lex = lexer.getLexeme();
                actual.add(lex.text);
            }}
            catch (IOException | LexerException e) {
            caught = e;
        }
        assertNotNull(caught);
        assertSame(LexerException.class, caught.getClass());
    }
}