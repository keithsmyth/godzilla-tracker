package com.keithsmyth.godzillatracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keithsmyth.godzillatracker.R;
import com.keithsmyth.godzillatracker.models.Quake;

import java.util.List;

/**
 * RecyclerAdapter to display a simple list of Quakes
 *
 * @author keithsmyth
 */
public class QuakeAdapter extends RecyclerView.Adapter<QuakeAdapter.QuakeViewHolder> {

  private final List<Quake> quakeList;

  public QuakeAdapter(List<Quake> quakeList) {
    this.quakeList = quakeList;
  }

  @Override public QuakeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_quake_row,
        viewGroup, false);
    return new QuakeViewHolder(view);
  }

  @Override public void onBindViewHolder(QuakeViewHolder quakeViewHolder, int i) {
    quakeViewHolder.bind(quakeList.get(i));
  }

  @Override public int getItemCount() {
    return quakeList.size();
  }

  /**
   * ViewHolder implementation for {@code QuakeAdapter}
   *
   * @author keithsmyth
   */
  public static class QuakeViewHolder extends RecyclerView.ViewHolder {

    private final TextView mTitleText;
    private final TextView mDescText;

    public QuakeViewHolder(View itemView) {
      super(itemView);
      mTitleText = (TextView) itemView.findViewById(R.id.txt_quake_title);
      mDescText = (TextView) itemView.findViewById(R.id.txt_quake_desc);
    }

    public void bind(Quake model) {
      mTitleText.setText(String.format("%1$s - %2$s", model.src, model.magnitude));
      mDescText.setText(model.region);
    }
  }
}
