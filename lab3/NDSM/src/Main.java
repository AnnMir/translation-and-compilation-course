import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Wrong parametrs");
            return;
        }
        try {
            File fsm = new File(args[0]);
            File string = new File(args[1]);
            NDSM_Config config = new NDSM_Config(fsm);
            config.parse();
            BufferedReader reader = new BufferedReader(new FileReader(string));
            config.recognise(reader);
        } catch (IOException | NDSM_Exception e) {
            e.printStackTrace();
        }
    }
}
