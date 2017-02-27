package millionaires;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rent.mysdaapp.R;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MillionairesActivity extends AppCompatActivity implements View.OnClickListener {

    private Drawable defBackground;
    private static final String INDEX_KEY = "index_key";
    private int currentIndex;
    private int questionsAmount;
    private boolean wasClicked = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.millionairs_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentIndex = getIntent().getIntExtra(INDEX_KEY, 0);


        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(10000);

        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });

        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(MillionairesActivity.this, MillionairesActivity.class);
                intent.putExtra(INDEX_KEY, ++currentIndex);
                startActivity(intent);
            }
        });
        progressBar.setProgress(0);
        objectAnimator.start();

        String json = null;

        try {
            json = loadJson();
            QuizConatainer quizConatainer = new Gson().fromJson(json, QuizConatainer.class);

            questionsAmount = quizConatainer.getQuestionsCount();

            TextView textView = (TextView) findViewById(R.id.text_view);
            QuestionsItems questionsItems = quizConatainer.getQuestions().get(currentIndex);
            textView.setText(questionsItems.getQuestion());


            Button answer1button = (Button) findViewById(R.id.answer_1);
            Button answer2button = (Button) findViewById(R.id.answer_2);
            Button answer3button = (Button) findViewById(R.id.answer_3);
            Button answer4button = (Button) findViewById(R.id.answer_4);

            SingleAnswer firstAnswer = questionsItems.getAnswers().get(0);
            answer1button.setText(firstAnswer.getText());
            answer1button.setTag(firstAnswer.isCorrect());

            SingleAnswer secondAnswer = questionsItems.getAnswers().get(1);
            answer2button.setText(secondAnswer.getText());
            answer2button.setTag(secondAnswer.isCorrect());

            SingleAnswer thirdAnswer = questionsItems.getAnswers().get(2);
            answer3button.setText(thirdAnswer.getText());
            answer3button.setTag(thirdAnswer.isCorrect());

            SingleAnswer fourthAnswer = questionsItems.getAnswers().get(3);
            answer4button.setText(fourthAnswer.getText());
            answer4button.setTag(fourthAnswer.isCorrect());

            answer1button.setOnClickListener(this);
            answer2button.setOnClickListener(this);
            answer3button.setOnClickListener(this);
            answer4button.setOnClickListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void playMusic() {

        String uri = "http://www.kakofonia.pl/PL/PLtele/plum.wav";
        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            player.setDataSource(uri);
            player.prepare();
            player.start();
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View view) {

        if (!wasClicked) {
            if ((Boolean) view.getTag()) {
                Toast.makeText(view.getContext(), "Poprawna odpowiedź", Toast.LENGTH_LONG).show();
                view.setBackgroundColor(Color.GREEN);
            } else {
                Toast.makeText(view.getContext(), "Zła odpowiedź", Toast.LENGTH_LONG).show();
                view.setBackgroundColor(Color.RED);
            }

            view.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent intent = new Intent(MillionairesActivity.this, MillionairesActivity.class);
                    intent.putExtra(INDEX_KEY, ++currentIndex);
                    startActivity(intent);
                }
            }, 2000);
            wasClicked = true;
        }

    }

    private String loadJson() throws IOException {

        StringBuilder buf = new StringBuilder();
        InputStream json = getAssets().open("quiz_data");
        BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;

        while ((str = in.readLine()) != null) {
            buf.append(str);
        }
        in.close();
        return buf.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
