package com.hillhouse.sriparnachakraborty.mplayer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.hillhouse.sriparnachakraborty.mplayer.listfragments.AudioListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_layout);

    }

    @Override
    protected void onStart(){
        super.onStart();
        Fragment fragment = new AudioListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(android.R.id.content, fragment);
        fragmentTransaction.commit();

        View decorView = getWindow().getDecorView();

        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
