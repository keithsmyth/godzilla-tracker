package com.keithsmyth.godzillatracker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.keithsmyth.godzillatracker.App;
import com.keithsmyth.godzillatracker.R;
import com.keithsmyth.godzillatracker.adapters.QuakeAdapter;
import com.keithsmyth.godzillatracker.models.Quake;
import com.keithsmyth.godzillatracker.models.Seismic;
import com.keithsmyth.godzillatracker.providers.Callback;

import java.util.List;

/**
 * @author keithsmyth
 */
public class MainFragment extends Fragment {

  private RecyclerView mQuakeRecyclerView;
  private View mEmptyView;
  private ProgressBar mProgress;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    mQuakeRecyclerView = (RecyclerView) view.findViewById(R.id.lst_quakes);
    mQuakeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mQuakeRecyclerView.setHasFixedSize(true);
    mEmptyView = view.findViewById(R.id.txt_empty);
    mProgress = (ProgressBar) view.findViewById(R.id.progress);
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    showLoading(true);
    App.getSeismicProvider().getRecentQuakes(new Callback<Seismic>() {
      @Override public void onSuccess(Seismic seismic) {
        showLoading(false);
        populateData(seismic.earthquakes);
      }

      @Override public void onFail(String msg) {
        showLoading(false);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void showLoading(boolean loading) {
    mProgress.setVisibility(loading ? View.VISIBLE : View.GONE);
    if (loading) {
      mEmptyView.setVisibility(View.GONE);
    }
  }

  private void populateData(List<Quake> quakeList) {
    if (getView() == null) return;
    mQuakeRecyclerView.setAdapter(new QuakeAdapter(quakeList));
    mEmptyView.setVisibility(quakeList.isEmpty() ? View.VISIBLE : View.GONE);
  }
}
