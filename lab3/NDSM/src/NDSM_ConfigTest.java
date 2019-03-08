import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

import static org.junit.Assert.*;

public class NDSM_ConfigTest {

    @Test
    public void parse() {
        try {
            File fsm = new File("src/files/ndsm.txt");
            NDSM_Config config = new NDSM_Config(fsm);
            config.parse();
            int j = config.getFinalStates(0);
            int i = 3;
            assertEquals(i, j);
        } catch (NDSM_Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void recognise() {
        try {
            File fsm = new File("src/files/ndsm.txt");
            File str = new File("src/files/str.txt");
            NDSM_Config config = new NDSM_Config(fsm);
            config.parse();
            BufferedReader reader = new BufferedReader(new FileReader(str));
            config.recognise(reader);
            String s = "String recognised";
            String msg = config.getMsg();
            assertEquals(s, msg);
        } catch (NDSM_Exception | IOException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void ParseWithEmptyFinalStates() throws Exception {
        expected.expect(NDSM_Exception.class);
        expected.expectMessage("No final states");
        File fsm = new File("src/files/empty.txt");
        NDSM_Config config = new NDSM_Config(fsm);
        config.parse();
    }

    @Test
    public void Notrecognised() {

        try {
            File fsm = new File("src/files/ndsm.txt");
            File str = new File("src/files/notrecognised.txt");
            NDSM_Config config = new NDSM_Config(fsm);
            config.parse();
            BufferedReader reader = new BufferedReader(new FileReader(str));
            config.recognise(reader);
            String s = "String wasn't recognised";
            String msg = config.getMsg();
            assertEquals(s, msg);
        } catch (NDSM_Exception | IOException e) {
            e.printStackTrace();
        }
    }
}