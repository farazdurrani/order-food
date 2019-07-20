package com.restaurant.CSR.ENTITY;

import java.beans.PropertyEditorSupport;

public class MenuTypeEditor extends PropertyEditorSupport {

    public void setAsText(String text) { 
        setValue(new Menu(text.toUpperCase()));
    }
}