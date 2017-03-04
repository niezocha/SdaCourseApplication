package millionaires;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rent.mysdaapp.MainActivity;
import com.example.rent.mysdaapp.R;

import org.w3c.dom.Text;

public class SummaryActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_activity);

        TextView resultYES = (TextView) findViewById(R.id.good_answers);
        TextView resultNO = (TextView) findViewById(R.id.bad_answers);

        resultYES.setText("Poprawnych odpowiedzi " + getIntent().getIntExtra(MillionairesActivity.CORRECT_ANSWERS,0));
        resultNO.setText("Niepoprawnych odpowiedzi "+ getIntent().getIntExtra(MillionairesActivity.INCORRECT_ANSWERS,0));

        Button button = (Button) findViewById(R.id.sum_goback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryActivity.this, MillionairesActivity.class);
                startActivity(intent);
            }
        });

        Button menuButton = (Button) findViewById(R.id.go_back_to_main);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
