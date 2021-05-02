package com.xelorium.soccerleaguetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.xelorium.soccerleaguetable.databinding.ItemTeamBinding;
import com.xelorium.soccerleaguetable.model.TeamModel;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    private Context context;
    private List<TeamModel> teamList;

    public TeamListAdapter(Context context, List<TeamModel> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    public void setTeamList(List<TeamModel> teamList) {
        this.teamList = teamList;
        notifyDataSetChanged();
    }

    public void getAllTeams(List<TeamModel> teamList)
    {
        this.teamList=teamList;
    }

    @NonNull
    @Override
    public TeamListAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TeamViewHolder(ItemTeamBinding.inflate(LayoutInflater.from(context)));

    }

    @Override
    public void onBindViewHolder(@NonNull TeamListAdapter.TeamViewHolder holder, int position) {
        holder.binding.tvItemTeamName.setText(this.teamList.get(position).getPosition() + "-) "+this.teamList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (this.teamList != null) {
            return this.teamList.size();
        }
        return 0;
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {

        private ItemTeamBinding binding;


        public TeamViewHolder(ItemTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
