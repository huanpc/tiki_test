import java.io.InvalidObjectException;
import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * @author huanpc (07/2018)
 * Product object class
 */
public class Product {
    private String id;
    private String name;
    private String color;
    private long price;

    public Product(String id, String name, String color, long price) throws IllegalArgumentException {
        if(Objects.isNull(id) || id.equals("") || Objects.isNull(name) || name.equals(""))
            throw new IllegalArgumentException("id or name is empty");
        if(price < 0)
            throw new IllegalArgumentException("price value is invalid ( must > 0)");
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if(Objects.isNull(name) || name.equals(""))
            throw new IllegalArgumentException("name is empty");
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) throws IllegalArgumentException {
        if(price < 0)
            throw new IllegalArgumentException("price value is invalid ( must > 0)");
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws IllegalArgumentException {
        if(Objects.isNull(id) || id.equals(""))
            throw new IllegalArgumentException("id is empty");
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
