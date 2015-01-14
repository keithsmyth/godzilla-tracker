package com.keithsmyth.godzillatracker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.keithsmyth.godzillatracker.App;
import com.keithsmyth.godzillatracker.R;
import com.keithsmyth.godzillatracker.models.Quake;
import com.keithsmyth.godzillatracker.models.Seismic;
import com.keithsmyth.godzillatracker.providers.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author keithsmyth
 */
public class MapFragment extends Fragment {

  private GoogleMap mMap;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_map, container, false);
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    App.getSeismicProvider().getRecentQuakes(new Callback<Seismic>() {
      @Override public void onSuccess(Seismic seismic) {
        trySetupMap(seismic.earthquakes);
      }

      @Override public void onFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void trySetupMap(List<Quake> quakeList) {
    if (mMap == null) {
      mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map))
          .getMap();
      if (mMap != null) {
        setupMap(quakeList);
      }
    }
  }

  private void setupMap(List<Quake> quakeList) {
    List<LatLng> points = new ArrayList<>();
    for (Quake quake : quakeList) {
      points.add(new LatLng(quake.lat, quake.lon));
    }
    HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
        .data(points)
        .build();
    mMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
    mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
  }
}
