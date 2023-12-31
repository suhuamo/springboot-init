package com.suhuamo.init.util;

/**
 * @author suhuamo
 * @date 2023-12-31
 * 线程局部变量，当前用于保存登录者信息
 */
public class ThreadLocalUtil {
    private final static ThreadLocal<Integer> local = new ThreadLocal<Integer>();

    public static void setUserId(Integer userId){
        local.set(userId);
    }

    public static Integer getUserId(){
        return local.get();
    }

    public static void remove(){
        if(local.get() != null){
            local.remove();
        }
    }

}
