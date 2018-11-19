package top.golabe.library;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import top.golabe.library.interfaces.IGoUser;
import top.golabe.library.interfaces.IJsonConverter;

public class SpUser implements IGoUser {
    private SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor mEditor;
    private IJsonConverter mJsonConverter;
    private Class<?>mClass;

    public SpUser(Context context, IJsonConverter jsonConverter, Class<?> cls) {
        mSharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        this.mClass=cls;
        this.mJsonConverter=jsonConverter;
    }


    @Override
    public <T>T getUser() {
        String user = mSharedPreferences.getString("user_info", "");
        return (T) mJsonConverter.fromJson(user,mClass);
    }



    @Override
    public <T> void setUser(T user) {
        if (user != null) {
            String toJson = mJsonConverter.toJson(user);
            mEditor.putString("user_info", toJson).apply();
        }
    }

    @Override
    public String getUserToken() {
        return mSharedPreferences.getString("user_token", "");
    }

    @Override
    public void setUserToken(String token) {
        if (!TextUtils.isEmpty(token)){
            mEditor.putString("user_token", token).apply();
        }
    }

    @Override
    public boolean isLogin() {
        return getUser()!=null;
    }

    @Override
    public void logout() {
        mEditor.clear().apply();
    }
}
