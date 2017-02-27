package drawingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.rent.mysdaapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StreamCorruptedException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gallery.GalleryActivity;

public class MainActivity extends AppCompatActivity {

    public static final String DRAWING_GALLERY_DIR = "drawing_gallery_dir";
    private SimpleDrawingView simpleDrawingView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_view);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        Button yellowButton = (Button) findViewById(R.id.yellow_button);
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.yellow));
            }
        });

        Button redButton = (Button) findViewById(R.id.red_button);
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.red));
            }
        });

        Button greenButton = (Button) findViewById(R.id.green_button);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.green));
            }
        });

        Button blueButton = (Button) findViewById(R.id.blue_button);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
            }
        });

        Button blackButton = (Button) findViewById(R.id.black_button);
        blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(MainActivity.this, R.color.black));
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
        else if (item.getItemId() == R.id.save_image){
            saveDrawingToFile();
        }
        else if (item.getItemId() == R.id.drawing_gallery){
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDrawingToFile() {
        File drawingFile = new File(getGalleryDirectory(), createFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(drawingFile);
            Bitmap bitmap = convertViewToBitmap(simpleDrawingView);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private String createFileName(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "my_drawing" + timeStamp + ".png";
    }

    private File getGalleryDirectory(){
        return getExternalFilesDir(DRAWING_GALLERY_DIR);
    }

    private Bitmap convertViewToBitmap(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

}
