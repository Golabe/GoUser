package top.golabe.library;


import android.content.Context;

import top.golabe.library.interfaces.IGoUser;
import top.golabe.library.interfaces.IJsonConverter;

public class GoUser implements IGoUser {

    private Context mContext;
    private Class<?> mClass;
    private IJsonConverter mJsonConverter;
    private IGoUser mIGoUser;

    public GoUser convert(IJsonConverter jsonConverter) {
        this.mJsonConverter = jsonConverter;
        return this;
    }


    public GoUser userClass(Class<?> cls) {
        this.mClass = cls;
        return this;
    }

    public GoUser init(Context context) {
        this.mContext = context;
        return this;
    }

    @Override
    public <T> T getUser() {
        return mIGoUser.getUser();
    }

    @Override
    public <T> void setUser(T user) {
        mIGoUser.setUser(user);
    }

    @Override
    public String getUserToken() {
        return mIGoUser.getUserToken();
    }

    @Override
    public void setUserToken(String token) {
        mIGoUser.setUserToken(token);
    }

    @Override
    public boolean isLogin() {
        return mIGoUser.isLogin();
    }

    @Override
    public void loginOut() {
        mIGoUser.loginOut();
    }

    public void build(IGoUser goUser) {
        mIGoUser = goUser;
    }

    public void build() {
        mIGoUser = new SpUser(mContext, mJsonConverter, mClass);
    }

    private static class SingletonHolder {
        private static final GoUser INSTANCE = new GoUser();
    }

    private GoUser() {

    }

    public static GoUser getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
