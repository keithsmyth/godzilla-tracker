package com.keithsmyth.godzillatracker.ui;

import android.app.Activity;
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
import com.google.maps.android.heatmaps.WeightedLatLng;
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
  private MapFragmentListener mListener;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_map, container, false);
    view.findViewById(R.id.btn_list).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mListener.onListButtonClick();
      }
    });
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tryCreateMap();
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof MapFragmentListener) {
      mListener = (MapFragmentListener) activity;
    } else {
      throw new ClassCastException("Activity required to be of type MapFragmentListener");
    }
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

  private SupportMapFragment getMapFragment() {
    return (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
  }

  private void trySetupMap(List<Quake> quakeList) {
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

  public interface MapFragmentListener {
    void onListButtonClick();
  }
}
