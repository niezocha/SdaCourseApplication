package fortunetale;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rent.mysdaapp.R;
import com.squareup.seismic.ShakeDetector;

import java.util.Random;

@RequiresApi(api = VERSION_CODES.LOLLIPOP)
public class FortunetaleActivity extends AppCompatActivity implements ShakeDetector.Listener {

    private FrameLayout layer;
    private Random random = new Random();
    private String[] fortunetale = new String[]{ "Kto błędy swe dostrzegł, więcej nie błądzi.",
            "Dla mężczyzny cnota jest talentem, dla kobiet brak talentów jest cnotą",
            "Zignoruj poprzednią wróżbę",
            "Fatal error",
            "Dbaj o zbilansowaną dietę - po kawałku cistka w każdej dłoni",
            "Nigdy nie zapominaj o przyjaciołach. Szczególnie jeśli wiszą Ci kasę",
            "Odnajdziesz satysfakcję z nauki w piątek wieczorem",
            "Wróżba której szukasz, jest w chińskim ciasteczku",
            "Wprowadź więcej danych",
            "Wszechświat wybrał cię na międzygalaktycznego wodzireja"};
    private Animator hidingAnimator;
    private Animator showingAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        layer = (FrameLayout) findViewById(R.id.fortune_container);

        FrameLayout parentLayout = (FrameLayout) findViewById(R.id.parent_layout);

        parentLayout.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateCircularReveal((int) event.getX(), (int) event.getY(), layer);
                }
                return true;
            }
        });

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

    }

    private void animateCircularReveal(int x, int y, final FrameLayout layer) {

        if((hidingAnimator != null && hidingAnimator.isRunning()) || showingAnimator != null &&showingAnimator.isRunning()){
            return;
        }

        if (layer.getVisibility() == View.VISIBLE) {
            Animator hidingAnimator =  ViewAnimationUtils
                    .createCircularReveal(layer, x, y, layer.getHeight(), 0);
            hidingAnimator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }
            });
            hidingAnimator.start();
        } else {
            int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            layer.setBackgroundColor(color);
            TextView textView = (TextView) findViewById(R.id.fortune_text);
            textView.setText(fortunetale[random.nextInt(fortunetale.length)]);
            showingAnimator = ViewAnimationUtils
                    .createCircularReveal(layer, x, y, 0, layer.getHeight());
            layer.setVisibility(View.VISIBLE);
            showingAnimator.start();
        }
    }

    @Override
    public void hearShake() {

        animateCircularReveal(random.nextInt(layer.getWidth()), random.nextInt(layer.getHeight()), layer);
    }
}
