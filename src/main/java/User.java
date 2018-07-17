/**
 * @author huanpc (07/2018)
 */
public class User {
    private String name;
    private String email;
    private UserGroup userGroup;
    private ShoppingCart shoppingCart;

    public User() {
        this.shoppingCart = new ShoppingCart();
        this.shoppingCart.setUser(this);
    }

    public User(String name, String email, UserGroup userGroup) {
        this.name = name;
        this.email = email;
        this.userGroup = userGroup;
        this.shoppingCart = new ShoppingCart();
        this.shoppingCart.setUser(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
