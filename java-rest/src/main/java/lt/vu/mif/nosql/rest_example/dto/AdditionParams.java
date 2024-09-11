package lt.vu.mif.nosql.rest_example.dto;

public class AdditionParams {
    private int a;
    private int b;

    public AdditionParams() {}

    public AdditionParams(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return this.a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
