package mvp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rent.mysdaapp.R;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;


@RequiresPresenter(MvpPresenter.class)
public class MvpActivity extends NucleusAppCompatActivity<MvpPresenter> {

    private TextView resultTextView;
    private static final String RESULT_KEY = "result_key";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resultTextView = (TextView) findViewById(R.id.result_textview);
        Button startTaskButton = (Button) findViewById(R.id.start_task_button);
        startTaskButton.setOnClickListener(v -> {
            resultTextView.setText("");
            getPresenter().executeLongRunningTask();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTextOnUiThread(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultTextView.setText(text);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT_KEY, resultTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resultTextView.setText(savedInstanceState.getString(RESULT_KEY));
    }
}
