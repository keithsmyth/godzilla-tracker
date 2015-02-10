package com.keithsmyth.godzillatracker.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.keithsmyth.godzillatracker.R;

/**
 * @author keithsmyth
 */
public class MainActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new MapFragment(), MapFragment.class.getName())
          .commit();
    }
  }
}
