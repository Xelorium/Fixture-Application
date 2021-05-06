package com.xelorium.soccerleaguetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xelorium.soccerleaguetable.R;
import com.xelorium.soccerleaguetable.model.TeamModel;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    private Context context;
    private List<TeamModel> teamList;
    private int lastIndex;

    public TeamListAdapter(Context context, List<TeamModel> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    public void getAllTeams(List<TeamModel> teamList) {
        this.teamList = teamList;
        lastIndex = teamList.size();
    }

    @NonNull
    @Override
    public TeamListAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_team, parent, false);
        TeamViewHolder vh = new TeamViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull TeamListAdapter.TeamViewHolder holder, int position) {

        TeamModel currentItem = teamList.get(position);

        if (!currentItem.getName().equals("") && !currentItem.getPosition().equals("")) {
            holder.teamName.setText(currentItem.getPosition() + ". " + currentItem.getName());
        }

    }

    @Override
    public int getItemCount() {
        if (this.teamList != null) {

            if (lastIndex>0){
                if (!teamList.get(lastIndex - 1).getName().equals("") && !teamList.get(lastIndex - 1).getPosition().equals("")) {
                    return this.teamList.size();
                } else {
                    return this.teamList.size() - 1;
                }
            }
            
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

    public class TeamViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView teamName;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            teamName = itemView.findViewById(R.id.tv_item_team_name);
        }

    }
}
