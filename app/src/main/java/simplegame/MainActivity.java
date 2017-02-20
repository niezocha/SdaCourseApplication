package simplegame;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.mysdaapp.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Integer> images = Arrays.asList(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five);
    private Random random = new Random();
    private ImageView firstImageView;
    private ImageView secondImageView;
    private Button player1;
    private Button player2;
    private CountDownTimer countDownTimer;
    private Button startButton;
    private boolean isRuning = false;
    private Drawable defBackground;
    private IntroAnimator introAnimator;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity_main);

        firstImageView = (ImageView) findViewById(R.id.image1);
        secondImageView = (ImageView) findViewById(R.id.image2);
        startButton = (Button) findViewById(R.id.start_button);
        exitButton = (Button) findViewById(R.id.exit_button);
        final TextView startText = (TextView) findViewById(R.id.start_text);
        introAnimator = new IntroAnimator(startText);

        player1 = (Button) findViewById(R.id.user1);
        defBackground = player1.getBackground();

        player2 = (Button) findViewById(R.id.user2);

        player1.setOnClickListener(this);
        player2.setOnClickListener(this);


        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int firstImage = images.get(random.nextInt(images.size()));
                int secondImage = images.get(random.nextInt(images.size()));

                firstImageView.setImageResource(firstImage);
                firstImageView.setTag(firstImage);
                secondImageView.setImageResource(secondImage);
                secondImageView.setTag(secondImage);
            }

            @Override
            public void onFinish() {
                startButton.setVisibility(View.VISIBLE);
                exitButton.setVisibility(View.VISIBLE);
                firstImageView.setVisibility(View.INVISIBLE);
                secondImageView.setVisibility(View.INVISIBLE);
                isRuning = false;
            }
        };

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player1.setBackground(defBackground);
                player2.setBackground(defBackground);

                introAnimator.showIntro(new Runnable() {
                    @Override
                    public void run() {
                        isRuning = true;
                        countDownTimer.start();
                        firstImageView.setVisibility(View.VISIBLE);
                        secondImageView.setVisibility(View.VISIBLE);
                    }
                });
                startButton.setVisibility(View.GONE);
                exitButton.setVisibility(View.GONE);

            }
        });

    }


    @Override
    public void onClick(View view) {
        if (!isRuning) {
            return;
        }

        if (firstImageView.getTag().equals(secondImageView.getTag())) {
            if (view.getId() == player1.getId()) {
                player1.setBackgroundColor(Color.GREEN);
                player2.setBackgroundColor(Color.RED);
//            Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
//                zamiast komunikatów zrobiliśmy kolory buttonów
            } else {
                player1.setBackgroundColor(Color.RED);
                player2.setBackgroundColor(Color.GREEN);
//            Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
            }
        } else {
            if (view.getId() == player1.getId()) {
                player1.setBackgroundColor(Color.RED);
                player2.setBackgroundColor(Color.GREEN);
//                Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
            } else {
                player1.setBackgroundColor(Color.GREEN);
                player2.setBackgroundColor(Color.RED);
//                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
            }
        }
        firstImageView.setVisibility(View.INVISIBLE);
        secondImageView.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
        exitButton.setVisibility(View.VISIBLE);
        countDownTimer.cancel();
        isRuning = false;
    }
}
