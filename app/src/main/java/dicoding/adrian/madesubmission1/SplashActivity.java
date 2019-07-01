package dicoding.adrian.madesubmission1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.madesubmission1.R;

public class SplashActivity extends AppCompatActivity {

    // Variables Declaration
    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Casting Variables
        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);

        // Transition Animation
        Animation splashTransition = AnimationUtils.loadAnimation(this, R.anim.splash_transition);
        tv.startAnimation(splashTransition);
        iv.startAnimation(splashTransition);

        // Intent to Main Activity
        final Intent i = new Intent(this, MainActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    // Lama SplashScreen
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

}