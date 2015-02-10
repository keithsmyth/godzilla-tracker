package com.keithsmyth.godzillatracker;

import android.app.Application;

/**
 * Extended to be a Singleton provider, we would separate if the application was more complex
 *
 * @author keithsmyth
 */
public class App extends Application {

  private static App sInstance;

  @Override public void onCreate() {
    super.onCreate();
    sInstance = this;
  }
}
