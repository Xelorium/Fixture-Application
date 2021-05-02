package com.xelorium.soccerleaguetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xelorium.soccerleaguetable.R;
import com.xelorium.soccerleaguetable.databinding.ItemFixtureWeekBinding;
import com.xelorium.soccerleaguetable.model.MatchModel;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private ArrayList<MatchModel> matchList;
    private Context context;

    public MatchAdapter(Context context, ArrayList<MatchModel> matchList){
        this.context = context;
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public MatchAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fixture_week, parent, false);
        MatchViewHolder vh = new MatchViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {

        holder.weekCount.setText(this.matchList.get(position).getWeekCount()+". HAFTA");
    }

    @Override
    public int getItemCount() {
        if (this.matchList != null){
            return this.matchList.size();
        }

        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView weekCount;
        private RecyclerView recyclerView;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);

            weekCount =itemView.findViewById(R.id.tv_item_fixture_week_count);
            recyclerView = itemView.findViewById(R.id.rv_fixture_week_match);

        }
    }
}
