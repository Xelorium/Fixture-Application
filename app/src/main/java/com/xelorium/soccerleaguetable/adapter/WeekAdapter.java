package com.xelorium.soccerleaguetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xelorium.soccerleaguetable.R;
import com.xelorium.soccerleaguetable.model.FixtureModel;
import com.xelorium.soccerleaguetable.model.MatchModel;
import com.xelorium.soccerleaguetable.model.TeamModel;
import com.xelorium.soccerleaguetable.model.WeekModel;

import java.util.ArrayList;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekViewHolder> {

    private ArrayList<FixtureModel> weekList;
    private ArrayList<TeamModel> teamList;
    private ArrayList<FixtureModel> fixtureList;
    private Context context;
    private boolean mBool;

    public WeekAdapter(Context context, ArrayList<FixtureModel> weekList) {
        this.context = context;
        this.weekList = weekList;
    }

    public void getAllTeamFixture(ArrayList<FixtureModel> weekList) {
        this.weekList = weekList;
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fixture_week, parent, false);
        WeekViewHolder vh = new WeekViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        FixtureModel currentItem = weekList.get(position);

        holder.weekCount.setText(currentItem.getWeekCount()+1+". HAFTA");
        holder.weekMatches.setText(currentItem.getMatchName());
//        WeekModel weekModel = this.weekList.get(position);
//
//        //200 = 10 * 20
////        fixtureList.get(position * teamSize, (position + 1) * teamSize)
//
//        ArrayList<MatchModel> matchModels = weekModel.getMatchObject();
//
//        String result = "";
//        for (int i = 0; i < matchModels.size(); i++) {
//
//            MatchModel matchModel = matchModels.get(i);
//            String homeTeam = matchModel.getHomeTeamName().toString();
//            String awayTeam = matchModel.getAwayTeamName().toString();
//
//            result += homeTeam + " - " + awayTeam + "\n";
//        }
//        holder.weekMatches.setText(result);


          /*  TextView tx = new TextView(context);
            tx.setHeight(100);
            tx.setWidth(100);
            tx.setText();
            holder.lv.addC(xtx) */

    }

    @Override
    public int getItemCount() {
        if (this.weekList != null) {
            return this.weekList.size();
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

    public class WeekViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView weekCount;
        private MaterialTextView weekMatches;

        public WeekViewHolder(@NonNull View itemView) {
            super(itemView);

            weekMatches = itemView.findViewById(R.id.tv_item_fixture_week_matches);
            weekCount = itemView.findViewById(R.id.tv_item_fixture_week_count);

        }
    }
}
