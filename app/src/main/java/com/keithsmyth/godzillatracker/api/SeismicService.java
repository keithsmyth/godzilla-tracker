package com.keithsmyth.godzillatracker.api;

import com.keithsmyth.godzillatracker.models.Seismic;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Retrofit service interface
 *
 * @author keithsmyth
 */
public interface SeismicService {

  public static final String BASE_URL = "http://www.seismi.org/api/";

  @GET("/eqs") void getRecentQuakes(Callback<Seismic> callback);
}
