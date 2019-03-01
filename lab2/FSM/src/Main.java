import java.io.*;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Wrong parametrs");
            return;
        }
        try {
            File fsm = new File(args[0]);
            File string = new File(args[1]);
            FSM_Config config = new FSM_Config(fsm);
            config.parse();
            BufferedReader reader = new BufferedReader(new FileReader(string));
            config.recognise(reader);
        } catch (IOException | FSM_Exception e) {
            e.printStackTrace();
        }

    }
}
