import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NDSM_Config {
    private File file;
    private ArrayList<Integer> next_states;
    private List<Integer> final_states;
    private Map<CurrentState, ArrayList<Integer>> transitions;
    private Stack<CurrentStep> stack;
    private String msg;

    NDSM_Config(File file) {
        this.file = file;
    }

    void parse() throws NDSM_Exception {
        try {
            final_states = new ArrayList<Integer>();
            transitions = new HashMap<>();
            next_states = new ArrayList<Integer>();
            stack = new Stack<>();
            CurrentState currentState;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String line : reader.readLine().split("\\s")) {
                if (line.length() == 0) {
                    throw new NDSM_Exception("No final states");
                } else {
                    final_states.add(Integer.parseInt(line));
                }
            }
            String state;
            while ((state = reader.readLine()) != null) {
                String str[] = state.split("\\s");
                if (str.length != 3) {
                    throw new NDSM_Exception("Wrong transition");
                } else {
                    ArrayList<Integer> next_state;
                    currentState = new CurrentState(Integer.parseInt(str[0]), str[1].charAt(0));
                    if (transitions.containsKey(currentState)) {
                        next_state = new ArrayList<>(transitions.get(currentState));
                    } else {
                        next_state = new ArrayList<>();
                    }
                    next_state.add(Integer.parseInt(str[2]));
                    transitions.put(currentState, next_state);
                }
            }
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    int getFinalStates(int i) {
        return final_states.get(i);
    }

    boolean isFinal(int state) {
        return final_states.contains(state);
    }

    ArrayList<Integer> next(int start, char symbol) {
        next_states.clear();
        transitions.forEach((key, value) -> {
            if (key.getState() == start && key.getSymbol() == symbol)
                next_states.addAll(value);
        });
        return next_states;
    }

    void recognise(BufferedReader reader) throws IOException, NDSM_Exception {
        String line;
        int current_state = 1;
        line = reader.readLine();
        if (line == null) {
            System.out.println("Empty line");
        }
        stack.push(new CurrentStep(current_state, line));
        while (!stack.empty()) {
            CurrentStep step = stack.pop();
            String str = step.getStr();
            if (step.getState() == 0) {
                throw new NDSM_Exception("Invalid transition! The string can't be recognised");
            }
            ArrayList<Integer> array = next(step.getState(), str.charAt(0));
            str = str.substring(1);
            if (!array.isEmpty()) {
                for (Integer state : array) {
                    if (isFinal(state) && str.length() == 0) {
                        msg = "String recognised";
                        System.out.println(msg);
                        return;
                    }
                    if (str.length() != 0) {
                        stack.push(new CurrentStep(state, str));
                    }
                }
            }
        }
        msg = "String wasn't recognised";
        System.out.println(msg);

    }

    public String getMsg() {
        return msg;
    }
}
