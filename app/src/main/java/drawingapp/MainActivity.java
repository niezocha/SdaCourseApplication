package drawingapp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.rent.mysdaapp.R;

public class MainActivity extends AppCompatActivity {

    private SimpleDrawingView simpleDrawingView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity_main);

        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_view);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setVisibility(View.INVISIBLE);

        Button yellowButton = (Button) findViewById(R.id.yellow_button);
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.yellow));
                radioGroup.setVisibility(View.INVISIBLE);
            }
        });

        Button redButton = (Button) findViewById(R.id.red_button);
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                radioGroup.setVisibility(View.INVISIBLE);
            }
        });

        Button greenButton = (Button) findViewById(R.id.green_button);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.green));
                radioGroup.setVisibility(View.INVISIBLE);
            }
        });

        Button blueButton = (Button) findViewById(R.id.blue_button);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
                radioGroup.setVisibility(View.INVISIBLE);
            }
        });

        Button blackButton = (Button) findViewById(R.id.black_button);
        blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                radioGroup.setVisibility(View.INVISIBLE);
            }
        });

    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.clear_menu){
            simpleDrawingView.clear();
        }

        if(item.getItemId() == R.id.select_color){
            radioGroup.setVisibility(View.VISIBLE);
        }

        if (item.getItemId() == R.id.save_image){

        }

        return super.onOptionsItemSelected(item);
    }
}
