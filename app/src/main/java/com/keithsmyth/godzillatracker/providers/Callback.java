package com.keithsmyth.godzillatracker.providers;

/**
 * Generic callback to help encapsulate app from api layer
 *
 * @author keithsmyth
 */
public interface Callback<T> {

  /**
   * The model has been retrieved successfully
   *
   * @param t the model instance being retrieved
   */
  void onSuccess(T t);

  /**
   * The model has not been retrieved successfully
   *
   * @param msg an error message that can be displayed to a user
   */
  void onFail(String msg);
}
