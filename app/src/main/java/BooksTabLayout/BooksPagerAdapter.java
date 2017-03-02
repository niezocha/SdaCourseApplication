package BooksTabLayout;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rent.mysdaapp.R;

public class BooksPagerAdapter extends PagerAdapter{



    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.single_book_item, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.single_book_image);


        container.addView(view);
        return  view;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
