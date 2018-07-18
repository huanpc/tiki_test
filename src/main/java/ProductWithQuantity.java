import java.util.Objects;

/**
 * @author huanpc (07/2018)
 * Define a object class which contains specific product with its quanity in shopping cart
 */
public class ProductWithQuantity {
    private int quantity;
    private Product product;

    public ProductWithQuantity(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWithQuantity that = (ProductWithQuantity) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product);
    }
}
