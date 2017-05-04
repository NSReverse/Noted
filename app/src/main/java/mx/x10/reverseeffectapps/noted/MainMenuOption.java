package mx.x10.reverseeffectapps.noted;

import android.widget.ImageView;

/**
 * Created by root on 5/3/17.
 */

public class MainMenuOption {
    private String title;
    private String subtitle;
    private int resId;

    public MainMenuOption(String title, String subtitle, int resId) {
        this.title = title;
        this.subtitle = subtitle;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getResId() {
        return resId;
    }
}
