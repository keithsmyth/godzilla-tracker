package com.keithsmyth.godzillatracker.providers;

import com.keithsmyth.godzillatracker.api.SeismicService;
import com.keithsmyth.godzillatracker.models.Seismic;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Abstraction layer between the data retrieval (retrofit api) and calling entity
 * <p/>
 * If we wanted several different implementations of this class an interface would be created
 *
 * @author keithsmyth
 */
public class SeismicProvider {

  private final RestAdapter mRestAdapter;
  private SeismicService mSeismicService;
  private Seismic mSeismicCache;

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

  /**
   * Returns all the recent earthquake information available
   *
   * @param callback generic {@code Callback} to facilitate success and fail scenarios
   */
  public void getRecentQuakes(final Callback<Seismic> callback) {
    if (mSeismicCache != null) callback.onSuccess(mSeismicCache);

    getSeismicService().getRecentQuakes(new retrofit.Callback<Seismic>() {
      @Override public void success(Seismic seismic, Response response) {
        mSeismicCache = seismic;
        callback.onSuccess(seismic);
      }

      @Override public void failure(RetrofitError error) {
        String msg = error != null ? error.getMessage() : null;
        callback.onFail(msg);
      }
    });
  }
}
