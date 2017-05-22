package com.nicksong.refreshAndLoad.widget.support.utils;

public class Utils {

    public static final boolean isClassExists(String classFullName) {
        try {
            Class.forName(classFullName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
