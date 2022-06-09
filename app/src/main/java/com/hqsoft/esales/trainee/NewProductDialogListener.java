package com.hqsoft.esales.trainee;

public interface NewProductDialogListener {
    void productAmountChange(Inventory inventory, int amount);
    void newProductClick();
    void cancelNewProduct();
}
