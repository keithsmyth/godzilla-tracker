package com.keithsmyth.godzillatracker.models;

import java.util.List;

/**
 * Simple POJO to hold a list of quakes, could be removed by using {@code ItemTypeAdapterFactory }
 *
 * @author keithsmyth
 */
public class Seismic {

  public long count;
  public List<Quake> earthquakes;
}
