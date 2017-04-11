package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Calvin on 23/03/2017.
 */

public class BlockComponent implements Component {

    //Size
    public static final float WIDTH = 1f; //Changing these values will effect the collision box
    public static final float HEIGHT = 1f;

    private boolean remove = false;

    private int indexRow = -1;
    private int indexCol = -1;


    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

    public int getIndexCol() {
        return indexCol;
    }

    public void setIndexCol(int indexCol) {
        this.indexCol = indexCol;
    }
}
