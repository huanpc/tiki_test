import java.util.Objects;

/**
 * @author huanpc (07/2018)
 * User detail
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

    public User(String name, String email, UserGroup userGroup) throws IllegalArgumentException{
        if(Objects.isNull(name) || name.equals("") || Objects.isNull(userGroup))
            throw new IllegalArgumentException("name or user group is empty");
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
        if(Objects.isNull(name) || name.equals(""))
            throw new IllegalArgumentException("name is empty");
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
        if(Objects.isNull(userGroup))
            throw new IllegalArgumentException("user group is empty");
        this.userGroup = userGroup;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
