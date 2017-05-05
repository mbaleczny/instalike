package pl.mbaleczny.instalike.app.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.mbaleczny.instalike.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_container, LoginFragment.newInstance(), LoginFragment.TAG)
                    .commit();
        }
    }
}
