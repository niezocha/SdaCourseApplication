package tablayout;

import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.mysdaapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class BooksPagerAdapter extends PagerAdapter {

    private SharedPreferences sharedPreferences;
    private List<Book> books;

    public BooksPagerAdapter(List<Book> books, SharedPreferences preferences) {
        this.books = books;
        this.sharedPreferences=preferences;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.single_book_item, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.single_book_image);
        image.setImageResource(books.get(position).getImageResId());

        TextView textView = (TextView) view.findViewById(R.id.book_description);
        textView.setText(books.get(position).getDescription());

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox.setChecked(books.get(position).isRead());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Book book = books.get(position);
                book.setRead(isChecked);
                sharedPreferences.edit().putBoolean(String.valueOf(book.getId()), book.isRead()).apply();
            }
        });


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return books.get(position).getTitle();
    }
}
