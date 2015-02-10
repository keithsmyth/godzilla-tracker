package com.keithsmyth.godzillatracker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;
import com.keithsmyth.godzillatracker.R;
import com.keithsmyth.godzillatracker.models.Quake;

import java.util.ArrayList;
import java.util.List;

/**
 * @author keithsmyth
 */
public class MapFragment extends Fragment {

  private GoogleMap mMap;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_map, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tryCreateMap();
  }

  private void tryCreateMap() {
    if (mMap == null) {
      SupportMapFragment fragment = getMapFragment();

      if (fragment == null) {
        fragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction()
            .add(R.id.map, fragment)
            .commit();
      }
    }
  }

  @Override public void onStart() {
    super.onStart();
    trySetupMap(new ArrayList<Quake>());
  }

  private SupportMapFragment getMapFragment() {
    return (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
  }

  private void trySetupMap(List<Quake> quakeList) {
    if (quakeList.isEmpty()) return;

    if (mMap == null) {
      SupportMapFragment fragment = getMapFragment();
      if (fragment != null) {
        mMap = fragment.getMap();
      }
      if (mMap == null) return;
    }
    List<WeightedLatLng> points = new ArrayList<>();
    for (Quake quake : quakeList) {
      points.add(new WeightedLatLng(new LatLng(quake.lat, quake.lon), quake.magnitude));
    }
    HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
        .weightedData(points)
        .build();
    mMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
  }
}
