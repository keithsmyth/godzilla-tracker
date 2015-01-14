package com.keithsmyth.godzillatracker;

import android.app.Application;

import com.keithsmyth.godzillatracker.providers.SeismicProvider;

/**
 * Extended to be a Singleton provider, we would separate if the application was more complex
 *
 * @author keithsmyth
 */
public class App extends Application {

  private static App sInstance;
  private SeismicProvider mSeismicProvider;

  @Override public void onCreate() {
    super.onCreate();
    sInstance = this;
  }

  /**
   * Return an instance of {@code SeismicProvider} to retrieve data from
   *
   * @return a single instance of {@code SeismicProvider}
   */
  public static SeismicProvider getSeismicProvider() {
    if (sInstance.mSeismicProvider == null) {
      sInstance.mSeismicProvider = new SeismicProvider();
    }
    return sInstance.mSeismicProvider;
  }
}
