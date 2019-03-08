public class CurrentState {
    private Integer state;
    private char symbol;

    CurrentState(Integer state, char symbol){
        this.state = state;
        this.symbol = symbol;
    }

    public Integer getState() {
        return state;
    }

    public char getSymbol() {
        return symbol;
    }
}
