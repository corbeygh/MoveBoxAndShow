package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Calvin on 3/05/2017.
 */

public class ShopComponent implements Component {

    public final static int ORE_SHOP_ID = 1;
    public final static int PICKAXE_SHOP_ID = 2;

    private int shopID = 0;

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }
}
