import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FSM_Config {

    private File file;
    private List<Integer> final_states;
    private List<Transition> transitions;
    private String msg;

    FSM_Config(File file){
        this.file = file;
    }

    void parse() throws FSM_Exception {
        try {
            final_states = new ArrayList<Integer>();
            transitions = new ArrayList<Transition>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for(String line : reader.readLine().split("\\s")){
                if(line.length() == 0){
                    throw new FSM_Exception("No final states");
                }else {
                    final_states.add(Integer.parseInt(line));
                }
            }
            String state;
            while((state = reader.readLine()) != null){
                String str[] = state.split("\\s");
                if(str.length != 3){
                    throw new FSM_Exception("Wrong transition");
                }else {
                    transitions.add(new Transition(Integer.parseInt(str[0]), str[1].charAt(0), Integer.parseInt(str[2])));
                }
            }
            reader.close();
            } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    int getFinalStates(int i){
        return final_states.get(i);
    }

    boolean isFinal(int state){
        return final_states.contains(state);
    }

    int next(int start, char symbol){
        for(Transition st : transitions){
            if(st.getStart_state() == start && st.getSymbol() == symbol)
                return st.getFinal_state();
        }
        return 0;
    }

    void recognise(BufferedReader reader) throws IOException, FSM_Exception {
        String line;
        int current_state = 1;
        line = reader.readLine();
        if(line == null){
            System.out.println("Empty line");
        }
        char[] buf = line.toCharArray();
        for(int i=0;i<buf.length;i++){
            current_state = next(current_state, buf[i]);
            if (current_state==0){
                throw new FSM_Exception("Invalid transition! The string can't be recognised");
            }
        }
        if (isFinal(current_state)){
            msg = "String recognised";
            System.out.println(msg);}
        else{
            msg = "String wasn't recognised";
            System.out.println(msg);}
    }

    public String getMsg() {
        return msg;
    }
}
