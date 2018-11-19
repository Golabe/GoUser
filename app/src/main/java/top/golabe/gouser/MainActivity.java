package top.golabe.gouser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import top.golabe.library.GoUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoUser.getInstance()
                .init(this)
                .userClass(UserInfo.class)
                .convert(new GsonConverterFactory())
                .build();

        GoUser.getInstance().setUser(new UserInfo("allen", "welcome to android", 1));

        UserInfo user = GoUser.getInstance().getUser();
        Toast.makeText(this, "username:" + user.getUsername(), Toast.LENGTH_SHORT).show();

        GoUser.getInstance().setUserToken("this is token");
        boolean login = GoUser.getInstance().isLogin();
        Toast.makeText(this, "isLogin:" + login, Toast.LENGTH_SHORT).show();


    }
}
