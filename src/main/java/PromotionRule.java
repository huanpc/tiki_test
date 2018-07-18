/**
 * @author huanpc (07/2018)
 * Define a promotion rule to consider when applying a discount
 */
public class PromotionRule {
    private long fromDate;
    private long toDate;
    private String color;
    private long subTotal;
    private long discount;
    private UserGroup userGroup;

    public PromotionRule(long fromDate, long toDate, String color, long subTotal, long discount, UserGroup userGroup) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.color = color;
        this.subTotal = subTotal;
        this.discount = discount;
        this.userGroup = userGroup;
    }

    public long getFromDate() {
        return fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public long getToDate() {
        return toDate;
    }

    public void setToDate(long toDate) {
        this.toDate = toDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }
}
