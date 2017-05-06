package pl.mbaleczny.instalike.app.news;

import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.lang.ref.WeakReference;

public class ImageLoadRequestListener implements RequestListener<String, GlideDrawable> {

    private WeakReference<ProgressBar> progressBar;

    public ImageLoadRequestListener(ProgressBar progressBar) {
        this.progressBar = new WeakReference<>(progressBar);
    }

    @Override
    public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                               boolean isFirstResource) {
        progressBar.get().setVisibility(View.GONE);
        return false;
    }

    @Override
    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> t,
                                   boolean isFromMemoryCache, boolean isFirstResource) {
        progressBar.get().setVisibility(View.GONE);
        return false;
    }
}
