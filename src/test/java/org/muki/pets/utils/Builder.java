package org.muki.pets.utils;

import org.springframework.util.Assert;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Builder<T> {

    private final Type type;
    private volatile Constructor<?> constructor;

    private Map<String, Object> fields = new HashMap<String, Object>();

    public Builder(Type type) {
        this.type = type;
    }

    // protected so it can  be used only in sub classes
    protected Builder() {

        Type superclass = getClass().getGenericSuperclass();

        if (superclass instanceof Class) {
            throw new RuntimeException("no parameter provided ... no type could be inferred");
        }

        this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

    /**
     * Instantiates a new instance of {@code T} using the default, no-arg constructor.
     */
    @SuppressWarnings("unchecked")
    private T newInstance() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {

        if (constructor == null) {
            if (type instanceof Class<?>) {
                constructor = ((Class<?>) type).getConstructor();
            } else {
                constructor = ((Class<?>) ((ParameterizedType) type).getRawType()).getConstructor();
            }
        }
        return (T) constructor.newInstance();
    }

    public Builder<T> field(String name, Object value) {
        fields.put(name, value);
        return this;
    }

    public Builder<T> set(String name, Object value) {
        fields.put(name, value);
        return this;
    }

    public T build() {
        try {
            T object = newInstance();

            for (Map.Entry<String, Object> field : fields.entrySet()) {
                setField(object, field.getKey(), field.getValue());
            }
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setField(Object object, String name, Object value) throws IllegalAccessException {
        Field field = getField(name, object.getClass());
        field.setAccessible(true);
        field.set(object, value);
    }

    private Field getField(String name, Class<?> clazz) {
        Field retValue = null;
        try {
            retValue = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                retValue = getField(name, superclass);
            }
        }

        Assert.state(retValue != null, "Field '" + name + "' doesn't exist in class " + type.toString());
        return retValue;
    }
}
