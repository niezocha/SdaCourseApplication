package millionaires;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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


    private List<QuestionsItems> questions = new ArrayList<QuestionsItems>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.millionairs_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(10000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        objectAnimator.start();

        String json = null;

        try {
            json = loadJson();
            QuizConatainer quizConatainer = new Gson().fromJson(json, QuizConatainer.class);

            TextView textView = (TextView) findViewById(R.id.text_view);
            textView.setText(quizConatainer.getQuestions().get(0).getQuestion());
            QuestionsItems questionsItems = quizConatainer.getQuestions().get(0);

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


    private void playMusic(){

        try {
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource("http://www.kakofonia.pl/PL/PLtele/plum.wav");
            player.prepare();
            player.start();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public void onClick(View v) {
        if((Boolean) v.getTag()){

        }

        playMusic();
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
