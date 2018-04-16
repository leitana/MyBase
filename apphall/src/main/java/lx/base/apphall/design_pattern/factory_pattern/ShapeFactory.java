package lx.base.apphall.design_pattern.factory_pattern;

import java.lang.reflect.InvocationTargetException;

/**
 * 工厂模式
 * 用反射替代if else
 * Created by 11300 on 2018/4/9.
 */

public class ShapeFactory {
    public <T> T getClass(Class<? extends Shape> clazz) {
        T obj = null;
        try {
            obj = (T) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
