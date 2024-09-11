package lt.vu.mif.nosql.rest_example.dto;

public class AdditionResult {
    private int result;

    public AdditionResult() {}

    public AdditionResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
