package com.aashish.daggerloginapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aashish.daggerloginapp.R;
import com.aashish.daggerloginapp.ui.profile.ProfileFragment;

public class MainActivity extends BaseActivity{
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProfileFragment();
    }

    private void addProfileFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, new ProfileFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_logout) {
                mSessionManager.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
