package tablayout;

import android.support.annotation.DrawableRes;

public class Book {

    private int id;
    private boolean isRead;
    @DrawableRes
    private int imageResId;
    private String title;
    private String description;

    public Book(int id, @DrawableRes int imageResId, String title, String description) {
        this.id=id;
        this.imageResId = imageResId;
        this.title=title;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @DrawableRes
    public int getImageResId() {
        return imageResId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
