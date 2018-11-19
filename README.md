# GoUser
## 登录成功之后用户信息保存工具
#### 方法
```java
//设置用户信息
GoUser.getInstance().setUser(new UserInfo("allen", "welcome to android", 1));

//获取用户信息
UserInfo user = GoUser.getInstance().getUser();

//设置token
GoUser.getInstance().setUserToken("this is token");

//获取token
GoUser.getInstance().getUserToken();

//是否登录
 boolean login = GoUser.getInstance().isLogin();
 
//退出登陆
 GoUser.getInstance().loginOut();
```

### 使用
#### Gradle 
```xml
```
#### 初始化工具
```java
 GoUser.getInstance()
                .init(this)
                .userClass(UserInfo.class)
                .convert(new GsonConverterFactory())
                .build();
```
- userClass 要序列化和反序列化的对象
- convert 主要作用的序列化和反序列化 ,这里可以使用自己喜欢的Json序列化工具实现，我用的是gson
```java
//实现IJsonConverter 接口，重写toJson、fromJson两个方法，具体自己实现。
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

```
- build 默认使用SharedPreferences持久化数据，也可以自定义。
- 使用build(IGoUser goUser)方法来自定义数据持久化。
```java

//实现IGoUser 接口 然后重写相关方法，自己实现持久化逻辑。
public class DbUser implements IGoUser {
    private SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor mEditor;
    private IJsonConverter mJsonConverter;
    private Class<?>mClass;

    public DbUser(Context context, IJsonConverter jsonConverter, Class<?> cls) {
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
    public void loginOut() {
        mEditor.clear().apply();
    }
}
```
