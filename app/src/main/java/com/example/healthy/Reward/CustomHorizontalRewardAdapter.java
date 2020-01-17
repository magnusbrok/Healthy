package com.example.healthy.Reward;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.Items.Item;
import com.example.healthy.logic.Items.Reward;

import java.util.ArrayList;

public class CustomHorizontalRewardAdapter extends RecyclerView.Adapter<CustomHorizontalRewardAdapter.RewardViewHolder> {
    Context mContext;
    ArrayList<Item> mRewards;

    public CustomHorizontalRewardAdapter(Context context, ArrayList<Item> rewards) {
        mContext = context;
        mRewards = rewards;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_custom_list_item, parent, false);
        RewardViewHolder viewHolder = new RewardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {
        Reward reward = (Reward) mRewards.get(position);
        holder.imageView.setImageResource(mRewards.get(position).getResID());
        holder.textView.setText(reward.getAmount()+"/"+AppLogic.getInstance().getTotalPrizes());
    }

    @Override
    public int getItemCount() {
        return mRewards.size();
    }

    public class RewardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        private Context mContext;

        public RewardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_horizontal_image);
            textView = itemView.findViewById(R.id.custom_horizontal_list_tv);
            mContext = itemView.getContext();
        }
    }
}