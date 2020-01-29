
public class Order {
    private Integer id;
    private String name;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Order(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    public Order(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Order() {
    }
}
