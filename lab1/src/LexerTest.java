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
            StringReader reader = new StringReader("5+-4()*5676/");
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
            List<String> expected = new ArrayList<>();
            while(lexer.current != -1){
                Lexeme lex = lexer.getLexeme();
                expected.add(lex.text);
            }
            List<String> actual = new ArrayList<>();
            actual.add(lexeme1.text);
            actual.add(lexeme2.text);
            actual.add(lexeme3.text);
            actual.add(lexeme4.text);
            actual.add(lexeme5.text);
            actual.add(lexeme6.text);
            actual.add(lexeme7.text);
            actual.add(lexeme8.text);
            actual.add(lexeme9.text);

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

            List<String> expected = new ArrayList<>();
            while(lexer.current != -1){
                Lexeme lex = lexer.getLexeme();
                expected.add(lex.text);
            }
            List<String> actual = new ArrayList<>();
            actual.add(lexeme1.text);
            actual.add(lexeme2.text);
            actual.add(lexeme3.text);
            Assert.assertEquals(expected, actual);

        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }
}