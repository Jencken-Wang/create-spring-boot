package com.wzg.util;

public class DoubleCheckSingleton {

    public static volatile DoubleCheckSingleton instance;

    private DoubleCheckSingleton() throws Exception {
        if (instance != null) {
            throw new Exception();
        }
    }

    public static DoubleCheckSingleton getInstance() throws Exception {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }



}
