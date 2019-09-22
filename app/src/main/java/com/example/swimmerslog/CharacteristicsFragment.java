package com.example.swimmerslog;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import androidx.appcompat.view.ActionMode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swimmerslog.add_goal.Goal;
import com.example.swimmerslog.add_training.Training;
import com.example.swimmerslog.common.TrainingGoalJoin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


public class CharacteristicsFragment extends Fragment  {

    public static final String TRAINING = "training";
    private RecyclerView goalRecyclerView;
    private CharacteristicListViewAdapter generalAdapter;

    private Training training;

    private ActionMode actionMode;
    private Goal editedGoal;
    private FloatingActionButton fab_add_goal;
    private View rootView;
    ArrayList<String> goalList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        goalList = new ArrayList<>();
        goalList.add("MOOD");
        goalList.add("TIREDNESS");
        goalList.add("Intensity of training");
        rootView =
                inflater.inflate(R.layout.characteristics_fragment, container, false);

        goalRecyclerView = rootView.findViewById(R.id.characteristicsRv);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        generalAdapter = new CharacteristicListViewAdapter(goalList);
        goalRecyclerView.setAdapter(generalAdapter);

        goalRecyclerView.setLayoutManager(layoutManager);
        initializeTraining();
        setAppBarTitle();
        return rootView;
    }

    private void initializeTraining(){
        training = new Training();
        training.date = new Date();
        training.id = UUID.randomUUID().toString();
    }

    private void setAppBarTitle() {
         SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM, dd - HH:mm", Locale.ENGLISH);
         String formatted = sdf.format(training.date);
        ((MainActivity) getActivity())
                .setActionBarTitle(formatted);
    }


    public void onRatingBarChange(String trainingId, String goal, float rating) {
        TrainingGoalJoin trainingGoalJoin = new TrainingGoalJoin(UUID.randomUUID().toString(), trainingId, goal, (int)rating);
    }

    /*public void showAlert(String text){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CharacteristicsFragment.this);
        alertDialog.setTitle("GOAL");
        alertDialog.setMessage(text);

        final EditText input = new EditText(CharacteristicsFragment.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        alertDialog.setView(input);

        alertDialog.setPositiveButton("YES",
                (dialog, which) -> insertGoal(input.getText().toString()));

        alertDialog.setNegativeButton("NO",
                (dialog, which) -> dialog.cancel());

        alertDialog.show();
    }*/

    private void insertGoal(String goalDescription) {
        if(goalDescription != null && !goalDescription.equals("")) {
            Goal goal = new Goal(UUID.randomUUID().toString(), goalDescription);
            insertGoal(goal);

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(TRAINING, training);
        super.onSaveInstanceState(outState);
    }


    public void insertGoal(Goal goal) {

    }

}


