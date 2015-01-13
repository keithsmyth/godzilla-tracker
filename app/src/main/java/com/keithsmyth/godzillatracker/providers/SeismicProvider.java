package com.keithsmyth.godzillatracker.providers;

import com.keithsmyth.godzillatracker.api.SeismicService;
import com.keithsmyth.godzillatracker.models.Seismic;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Abstraction layer between the data retrieval (retrofit api) and calling entity
 *
 * @author keithsmyth
 */
public class SeismicProvider {

  private final RestAdapter mRestAdapter;
  private SeismicService mSeismicService;

  public SeismicProvider() {
    mRestAdapter = new RestAdapter.Builder()
        .setEndpoint(SeismicService.BASE_URL)
        .build();
  }

  private SeismicService getSeismicService() {
    if (mSeismicService == null) {
      mSeismicService = mRestAdapter.create(SeismicService.class);
    }
    return mSeismicService;
  }

  public void getRecentQuakes(final Callback<Seismic> callback) {
    getSeismicService().getRecentQuakes(new retrofit.Callback<Seismic>() {
      @Override public void success(Seismic seismic, Response response) {
        callback.onSuccess(seismic);
      }

      @Override public void failure(RetrofitError error) {
        String msg = error != null ? error.getMessage() : null;
        callback.onFail(msg);
      }
    });
  }
}