import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huanpc (07/2018)
 * Test case
 */
public class ShoppingCartTest {
    User user;

    @Before
    public void setUp() throws Exception {
        System.out.println("## Start test");
        /*Create user*/
        user = new User("John Doe 1", "john.doe@example.com", UserGroup.GOLD);
        /*get cart*/
        ShoppingCart shoppingCart = user.getShoppingCart();
        /*add some product*/
        Product product1 = new Product("ID1", "Iphone Sliver", "Silver", 999);
        ProductWithQuantity productWithQuantity1 = new ProductWithQuantity(2, product1);
        shoppingCart.addProduct(productWithQuantity1);

        Product product2 = new Product("ID2", "Iphone Black", "Black", 899);
        ProductWithQuantity productWithQuantity2 = new ProductWithQuantity(1, product2);
        shoppingCart.addProduct(productWithQuantity2);
    }

    @Test
    public void test1(){
        ShoppingCart shoppingCart = user.getShoppingCart();
        /*billing*/
        long totalCost = shoppingCart.getTotalPriceToPay();
        long expectedCost = 999 * 2 + 899;
        Assert.assertEquals(expectedCost, totalCost);
    }

    @Test
    public void test2(){
        long currentTime = System.currentTimeMillis();
        /*some rule*/
        long startDate = currentTime - 2 * 24 * 3600 * 1000;
        long toDate = currentTime + 2 * 24 * 3600 * 1000;
        PromotionRule promotionRule = new PromotionRule(startDate, toDate,
                "Black", 1500, 50,
                UserGroup.GOLD);

        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.addPromotionRule(promotionRule);
        /*billing*/
        long totalCost = shoppingCart.getTotalPriceToPay();
        long expectedCost = 999 * 2 + 899;
        Assert.assertEquals(expectedCost, totalCost);
    }
}
