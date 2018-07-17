import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author huanpc (07/2018)
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

    public long getTotalDiscount(){
        long totalDiscount = 0L;
        long currentTimeStamp = System.currentTimeMillis();
        if(this.promotionRules.size() == 0)
            return totalDiscount;

        for (PromotionRule rule: this.promotionRules){
            if((rule.getFromDate() <= currentTimeStamp)
                    && (currentTimeStamp <= rule.getToDate())
                    && user.getUserGroup().equals(rule.getUserGroup())) {
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

    public long getTotalPrice() {
        long totalPrice = 0L;
        for(ProductWithQuantity productQuantity: this.listProductWithQuantity) {
            totalPrice += productQuantity.getQuantity() * productQuantity.getProduct().getPrice();
        }
        return totalPrice;
    }

    public long getTotalPriceToPay() {
        return getTotalPrice() - getTotalDiscount();
    }
}
