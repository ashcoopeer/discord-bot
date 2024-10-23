package com.ashcoopeer.discord.utils;

import java.util.List;

public abstract class Report<T> {

    protected List<T> data;

    public void setData(List<T> data) {
        this.data = data;
    }

    public T getElementByIndex(int index) {
        return this.data.get(index);
    }
}
