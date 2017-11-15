package entity;

public class Vertex {
    private String name;
    private int order;

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
