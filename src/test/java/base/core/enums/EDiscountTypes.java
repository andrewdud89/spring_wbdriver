package base.core.enums;

/**
 * used on discount coupon (carProvider+discountType+codeNumber)
 * Created by zhitnikov on 6/2/2017.
 */
public enum EDiscountTypes {

    CD("CD"),
    PC("PC"),
    IT("IT"),
    ID("ID"),
    NULL("NULL");

    public String discountType;

    EDiscountTypes(String discountType) {
        this.discountType = discountType;
    }
}

