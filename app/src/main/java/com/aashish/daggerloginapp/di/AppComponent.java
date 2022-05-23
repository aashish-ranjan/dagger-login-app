package com.aashish.daggerloginapp.di;

import android.app.Application;

import com.aashish.daggerloginapp.BaseApplication;
import com.aashish.daggerloginapp.SessionManager;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuildersModule.class, AppModule.class, ViewModelFactoryModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {
    SessionManager getSessionManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
