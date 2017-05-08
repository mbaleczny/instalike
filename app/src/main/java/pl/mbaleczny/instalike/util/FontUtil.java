package pl.mbaleczny.instalike.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import javax.inject.Inject;

public class FontUtil {

    private final Context ctx;

    @Inject
    public FontUtil(Context ctx) {
        this.ctx = ctx;
    }

    public TextView setMonserratLightFont(TextView t) {
        t.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Light.ttf"));
        return t;
    }

    public TextView setTrumpGothicEastBoldFont(TextView t) {
        t.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/trump_gothic_east_bold.ttf"));
        return t;
    }
}
