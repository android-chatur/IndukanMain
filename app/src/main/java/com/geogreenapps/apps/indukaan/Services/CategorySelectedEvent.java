package com.geogreenapps.apps.indukaan.Services;


import com.geogreenapps.apps.indukaan.classes.Category;

public class CategorySelectedEvent {
    public Category cats;

    public CategorySelectedEvent(Category cats) {
        this.cats = cats;
    }

}
