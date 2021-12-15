package flows;

public class Vertex {
    private int height;
    private int excess;
    private final int index;

    public Vertex(int i) {
        index = i;
        this.height=0;
        this.excess=0;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public void setExcess(int excess) {
        this.excess = excess;
    }

    public int getExcess() {
        return this.excess;
    }


    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        return "Excess: " +this.excess + " Height: " + this.height;
    }
}
