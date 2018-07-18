import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author huanpc (07/2018)
 * A shopping cart associated with specific user
 */
public class ShoppingCart {
    private List<ProductWithQuantity> listProductWithQuantity;
    private User user;
    private List<PromotionRule> promotionRules;

    public ShoppingCart() {
        this.listProductWithQuantity = new ArrayList<>();
        this.promotionRules = new ArrayList<>();
    }

    public ShoppingCart(List<ProductWithQuantity> products, User user) {
        this.listProductWithQuantity = products;
        this.user = user;
    }

    public List<ProductWithQuantity> getProducts() {
        return listProductWithQuantity;
    }

    public void setProducts(List<ProductWithQuantity> products) {
        this.listProductWithQuantity = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addProducts(List<ProductWithQuantity> newProducts) {
        this.listProductWithQuantity.addAll(newProducts);
    }

    public void addProduct(ProductWithQuantity newProduct) {
        this.listProductWithQuantity.add(newProduct);
    }

    public void removeProducts(List<ProductWithQuantity> remProducts) {
        if(this.listProductWithQuantity.size() > remProducts.size()) {
            this.listProductWithQuantity.removeAll(remProducts);
        }
    }

    public void removeProduct(ProductWithQuantity remProduct){
        if(this.listProductWithQuantity.size() > 0) {
            this.listProductWithQuantity.remove(remProduct);
        }
    }

    public List<PromotionRule> getPromotionRules() {
        return promotionRules;
    }

    public void setPromotionRules(List<PromotionRule> promotionRules) {
        this.promotionRules = promotionRules;
    }

    public void addPromotionRule(PromotionRule promotionRule) {
        this.promotionRules.add(promotionRule);
    }

    /**
     * Loop through each rule and apply discount if all rule conditions are met.
     * <p>
     * Rule conditions are <em>fromDate</em>, <em>toDate</em>, <em>userGroup</em>, <em>color&subTotal</em>, <em>discount</em>
     * <ul>Discount is apply if:
     *      <li>current timestamp is valid (fromDate < and < toDate) and user group is same with rule group</li>
     *      <li>or if total price of products having same color with rule color is greater than subtotal.</li>
     * </ul>
     * @return total discount amount or 0 if no rule is apply
     */
    public long getTotalDiscount(){
        long totalDiscount = 0L;
        long currentTimeStamp = System.currentTimeMillis();
        if(this.promotionRules.size() == 0)
            return totalDiscount;

        for (PromotionRule rule: this.promotionRules){
            /*check conditions*/
            if((rule.getFromDate() <= currentTimeStamp)
                    && (currentTimeStamp <= rule.getToDate())
                    && user.getUserGroup().equals(rule.getUserGroup())) {

                /*color and subtotal case*/
                if(Objects.nonNull(rule.getColor()) && rule.getSubTotal() > 0) {
                    long productColorPrice = 0;
                    for(ProductWithQuantity productQuantity: this.listProductWithQuantity) {
                        if(productQuantity.getProduct().getColor().equals(rule.getColor())) {
                            productColorPrice += productQuantity.getQuantity() * productQuantity.getProduct().getPrice();
                        }
                    }
                    if(productColorPrice >= rule.getSubTotal()) {
                        totalDiscount += rule.getDiscount();
                    }
                } else {
                    totalDiscount += rule.getDiscount();
                }
            }
        }

        return totalDiscount;
    }

    /**
     * Return total price of products without any discount
     * @return long value or 0 if there is no product in cart
     */
    public long getTotalPrice() {
        long totalPrice = 0L;
        for(ProductWithQuantity productQuantity: this.listProductWithQuantity) {
            totalPrice += productQuantity.getQuantity() * productQuantity.getProduct().getPrice();
        }
        return totalPrice;
    }

    /**
     * Return total price of products after discount
     * @return long value
     */
    public long getTotalPriceToPay() {
        return getTotalPrice() - getTotalDiscount();
    }
}
