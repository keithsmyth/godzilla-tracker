package com.keithsmyth.godzillatracker.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.keithsmyth.godzillatracker.R;


public class MainActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new MapFragment())
          .commit();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_about) {
      Toast.makeText(this, "Written by Keith Smyth", Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
