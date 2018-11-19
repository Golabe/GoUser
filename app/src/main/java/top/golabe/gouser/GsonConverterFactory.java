package top.golabe.gouser;

import com.google.gson.Gson;

import top.golabe.library.interfaces.IJsonConverter;

public class GsonConverterFactory<T> implements IJsonConverter<T> {
    private Gson mGson;

    public GsonConverterFactory() {
        this.mGson = new Gson();
    }

    @Override
    public String toJson(Object obj) {
        return mGson.toJson(obj);
    }

    @Override
    public T fromJson(String json, Class<?> cls) {
        return (T) mGson.fromJson(json,cls);
    }

}
