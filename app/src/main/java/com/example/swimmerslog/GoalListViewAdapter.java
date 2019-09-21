package com.example.swimmerslog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimmerslog.add_goal.Goal;
import com.example.swimmerslog.add_training.Training;
import com.example.swimmerslog.common.TrainingGoalJoin;

import java.util.ArrayList;
import java.util.List;

public class GoalListViewAdapter extends RecyclerView.Adapter<GoalListViewAdapter.CustomViewHolder> {

    private Training training;
    private OnItemClickListener listener;
    private ArrayList<String> goals;
    private CustomViewHolder viewHolder;
    private Context context;
    private List<TrainingGoalJoin> trainingGoalJoins;


    public void addGoal(Goal goal) {
        //goals.add(goal);

    }

    public void setTrainingGoals(List<TrainingGoalJoin> trainingGoalJoins) {
        this.trainingGoalJoins = trainingGoalJoins;
    }

    public interface OnItemClickListener {

        void onRatingBarChange(String trainingId, String goal, float rating);
    }

    public GoalListViewAdapter(OnItemClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        goals = new ArrayList<>();

    }
    public void setGoals(ArrayList<String> goals){
        this.goals = goals;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goal_layout, null);
        viewHolder = new CustomViewHolder(view, listener, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        int j = 0;
        customViewHolder.fillView(goals.get(i));
        if(trainingGoalJoins != null) {
            for (TrainingGoalJoin trainingGoalJoin : trainingGoalJoins) {
                if (trainingGoalJoin.goal.equals(goals.get(i))) {
                    customViewHolder.fillViewWithRating(trainingGoalJoin.rating);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null != goals ? goals.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView descriptionText;

        RatingBar ratingBar;

        Context context;


        CustomViewHolder(View view, OnItemClickListener listener, Context context) {
            super(view);
            this.context = context;
            this.descriptionText = view.findViewById(R.id.goalTextView);
            this.ratingBar = view.findViewById(R.id.ratingBar);
            ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
                if (fromUser) {
                    listener.onRatingBarChange(training.id, goals.get(getLayoutPosition()), rating);
                }
            });
        }

        void fillView(String text) {
            descriptionText.setText(text);

        }

        public void fillViewWithRating(int rating) {
            ratingBar.setRating(rating);
        }
    }

    private OnItemClickListener onItemClickListener;
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
