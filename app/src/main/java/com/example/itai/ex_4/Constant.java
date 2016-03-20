package com.example.itai.ex_4;

import android.provider.BaseColumns;

/**
 * Created by Itai on 20/03/2016.
 */
public class Constant {

    private Constant(){
        throw new AssertionError("Can't create constant");
    }

    public static abstract class Person implements BaseColumns{
        public static final String TABLE_NAME = "contactBook";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String PHONE_NUM = "phoneNum";

    }
}
