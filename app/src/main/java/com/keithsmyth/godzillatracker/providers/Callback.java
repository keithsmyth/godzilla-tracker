package com.keithsmyth.godzillatracker.providers;

/**
 * @author keithsmyth
 */
public interface Callback<T> {

  void onSuccess(T t);

  void onFail(String msg);
}
