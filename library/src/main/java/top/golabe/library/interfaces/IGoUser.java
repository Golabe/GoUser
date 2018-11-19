package top.golabe.library.interfaces;

public interface IGoUser {

    <T> T getUser();

    <T>  void setUser(T user);

    String getUserToken();
    void setUserToken(String token);

    boolean isLogin();
    void loginOut();

}
