package com.base.util;

import net.sf.cglib.beans.BeanCopier;

/**
 * Created by suvan on 2017/11/1.
 */
public class BeanCopyUtils {

    /**
     * 按照指定的类型进行复制
     * @param source
     * @param targetClz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static<T> T copy(Object source, Class<T> targetClz) throws InstantiationException, IllegalAccessException{
        T target = null;
        target = targetClz.newInstance();

        return (T) copy(source, target);
    }

    /**
     * 对象的属性值复制到另一个对象属性值
     * @param source
     * @param target
     * @return
     */
    public static<T> T copy(Object source, T target){
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);

        return target;
    }
}
