package top.golabe.library.interfaces;

public interface IJsonConverter<T> {
    String toJson(Object obj);
    T fromJson(String json, Class<?> cls);
}
