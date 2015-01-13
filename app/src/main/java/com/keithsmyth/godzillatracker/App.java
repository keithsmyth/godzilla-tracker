package com.keithsmyth.godzillatracker;

import android.app.Application;

import com.keithsmyth.godzillatracker.providers.SeismicProvider;

/**
 * @author keithsmyth
 */
public class App extends Application {

  private static App sInstance;
  private SeismicProvider mSeismicProvider;

  @Override public void onCreate() {
    super.onCreate();
    sInstance = this;
  }

  public static SeismicProvider getSeismicProvider() {
    if (sInstance.mSeismicProvider == null) {
      sInstance.mSeismicProvider = new SeismicProvider();
    }
    return sInstance.mSeismicProvider;
  }
}
