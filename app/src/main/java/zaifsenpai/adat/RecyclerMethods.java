package zaifsenpai.adat;

import android.widget.ImageView;

public interface RecyclerMethods {
    public void delete(int position);

    public void add(int position);

    public void setRandomImage(ImageView background, int position);
}
