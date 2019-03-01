import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

import static org.junit.Assert.*;

public class FSM_ConfigTest {

    @Test
    public void parse() {

        try {
            File fsm = new File("src/files/fsm.txt");
            FSM_Config config = new FSM_Config(fsm);
            config.parse();
            int j = config.getFinalStates(0);
            int i = 2;
            assertEquals(i, j);
        } catch (FSM_Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void recognise() {

        try {
            File fsm = new File("src/files/fsm.txt");
            File str = new File("src/files/str.txt");
            FSM_Config config = new FSM_Config(fsm);
            config.parse();
            BufferedReader reader = new BufferedReader(new FileReader(str));
            config.recognise(reader);
            String s = "String recognised";
            String msg = config.getMsg();
            assertEquals(s, msg);
        } catch (FSM_Exception | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void ParseWithEmptyFinalStates() throws Exception {
        expected.expect(FSM_Exception.class);
        expected.expectMessage("No final states");
        File fsm = new File("src/files/empty.txt");
        FSM_Config config = new FSM_Config(fsm);
        config.parse();
    }

    @Test
    public void RecogniseWithWrongTransition() throws Exception {
        expected.expect(FSM_Exception.class);
        expected.expectMessage("Invalid transition! The string can't be recognised");
        File fsm = new File("src/files/wrong.txt");
        File str = new File("src/files/str.txt");
        FSM_Config config = new FSM_Config(fsm);
        config.parse();
        BufferedReader reader = new BufferedReader(new FileReader(str));
        config.recognise(reader);
    }

    @Test
    public void Notrecognised() {

        try {
            File fsm = new File("src/files/fsm.txt");
            File str = new File("src/files/notrecognised.txt");
            FSM_Config config = new FSM_Config(fsm);
            config.parse();
            BufferedReader reader = new BufferedReader(new FileReader(str));
            config.recognise(reader);
            String s = "String wasn't recognised";
            String msg = config.getMsg();
            assertEquals(s, msg);
        } catch (FSM_Exception | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}