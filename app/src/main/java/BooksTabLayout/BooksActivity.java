package BooksTabLayout;

import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rent.mysdaapp.R;

public class BooksActivity extends AppCompatActivity{

    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.books_layout);

        viewPager = (ViewPager) findViewById(R.id.books_view_pager);


    }
}
