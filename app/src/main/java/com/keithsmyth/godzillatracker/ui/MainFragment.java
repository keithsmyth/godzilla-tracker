package com.keithsmyth.godzillatracker.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keithsmyth.godzillatracker.R;

/**
 * @author keithsmyth
 */
public class MainFragment extends Fragment {

  private RecyclerView mQuakeRecyclerView;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    mQuakeRecyclerView = (RecyclerView) view.findViewById(R.id.lst_quakes);
    mQuakeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mQuakeRecyclerView.setHasFixedSize(true);
    return view;
  }
}
