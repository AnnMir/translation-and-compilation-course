public class Transition {
    private int start_state;
    private char symbol;
    private int final_state;

    Transition(int s, char c, int f){
        this.start_state = s;
        this.symbol = c;
        this.final_state = f;
    }

    public int getStart_state() {
        return start_state;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getFinal_state() {
        return final_state;
    }
}
