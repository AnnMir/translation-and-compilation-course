public class CurrentStep {
    private Integer state;
    private String str;

    CurrentStep(Integer state, String str){
        this.state = state;
        this.str = str;
    }

    public Integer getState() {
        return state;
    }

    public String getStr() {
        return str;
    }
}
