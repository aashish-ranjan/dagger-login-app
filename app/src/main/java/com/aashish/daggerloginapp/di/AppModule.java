package com.aashish.daggerloginapp.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.aashish.daggerloginapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static RequestOptions provideRequestOptions() {
        return new RequestOptions()
                .error(R.drawable.white_background)
                .placeholder(R.drawable.white_background);
    }

    @Provides
    static Drawable providesLogo(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

    @Provides
    static RequestManager provideGlideInstance(Application application) {
        return Glide.with(application);
    }

}
