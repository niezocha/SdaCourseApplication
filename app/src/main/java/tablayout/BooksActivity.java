package tablayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.example.rent.mysdaapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.books_view_pager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Book effJava = new Book(1, R.drawable.eff_java, "Effective Java", "Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day.");
        effJava.setRead(sharedPreferences.getBoolean(String.valueOf(effJava.getId()), false));
        Book cleanCode = new Book(2, R.drawable.clean_code, "Clean Code", "Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn’t have to be that way.");
        cleanCode.setRead(sharedPreferences.getBoolean(String.valueOf(effJava.getId()), false));
        Book design = new Book(3, R.drawable.design_patterns, "Design Patterns", "At any given moment, someone struggles with the same software design problems you have. And, chances are, someone else has already solved your problem. This edition of Head First Design Patterns—now updated for Java 8—shows you the tried-and-true, road-tested patterns used by developers to create functional, elegant, reusable, and flexible software. By the time you finish this book, you’ll be able to take advantage of the best design practices and experiences of those who have fought the beast of software design and triumphed.");
        design.setRead(sharedPreferences.getBoolean(String.valueOf(effJava.getId()), false));

        List<Book> list = Arrays.asList(effJava, cleanCode, design);

        BooksPagerAdapter adapter = new BooksPagerAdapter(list, sharedPreferences);
        viewPager.setAdapter(adapter);

    }
}
