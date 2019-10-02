package dobrowol.swimmerlog;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dobrowol.swimmerlog.add_characteristic.Characteristic;
import dobrowol.swimmerlog.add_characteristic.CharacteristicViewModel;
import dobrowol.swimmerlog.add_characteristic.TrainingCharacteristic;
import dobrowol.swimmerlog.add_characteristic.TrainingCharacteristicViewModel;
import dobrowol.swimmerlog.add_training.Training;
import dobrowol.swimmerlog.add_training.TrainingViewModel;

import com.example.swimmerlog.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;


public class CharacteristicsFragment extends Fragment  implements CharacteristicListViewAdapter.OnItemClickListener{

    public static final String TRAINING = "training";
    private RecyclerView goalRecyclerView;
    private CharacteristicListViewAdapter generalAdapter;

    private Training training;

    private View rootView;

    private float rating;
    ArrayList<String> goalList;
    TrainingViewModel trainingViewModel;
    TrainingCharacteristicViewModel trainingCharacteristicViewModel;
    CharacteristicViewModel characteristicViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
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

        generalAdapter = new CharacteristicListViewAdapter(goalList, this);
        goalRecyclerView.setAdapter(generalAdapter);

        goalRecyclerView.setLayoutManager(layoutManager);

        initializeViewModels();

        if (getArguments() != null) {
            training = CharacteristicsFragmentArgs.fromBundle(getArguments()).getTraining();
        }
        else  {
            initializeTraining();
        }
        generalAdapter.setTraining(training);

        trainingCharacteristicViewModel.getTrainingCharacteristicByTrainingId(training.id).observe(this, trainingCharacteristic1->{
            if(trainingCharacteristic1!= null){
                generalAdapter.setTrainingCharacteristics(trainingCharacteristic1);
                generalAdapter.notifyDataSetChanged();
            }
        });
        setAppBarTitle();
        return rootView;
    }

    private void initializeViewModels(){
        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel.class);
        trainingCharacteristicViewModel = ViewModelProviders.of(this).get(TrainingCharacteristicViewModel.class);
        characteristicViewModel = ViewModelProviders.of(this).get(CharacteristicViewModel.class);

        for(String characteristic: goalList){
            insertCharacteristic(characteristic);
        }

        characteristicViewModel.characteristicWithDescription.observe(this, characteristic1 -> {
            if(characteristic1!= null){
                TrainingCharacteristic trainingCharacteristic = new TrainingCharacteristic(
                        UUID.randomUUID().toString(), training.id, characteristic1.id, (int)rating);
                trainingCharacteristicViewModel.insert(trainingCharacteristic);

            }
        });

    }
    private void initializeTraining(){
        training = new Training();
        training.date = new Date();
        training.id = UUID.randomUUID().toString();
        trainingViewModel.insert(training);
    }

    private void setAppBarTitle() {
         SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM, dd - HH:mm", Locale.ENGLISH);
         String formatted = sdf.format(training.date);
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(formatted);
    }


    public void onRatingBarChange(String trainingId, String characteristic, float rating) {
        this.rating = rating;
        characteristicViewModel.getCharacteristicWithDescription(characteristic);
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

    private void insertCharacteristic(String goalDescription) {
        if(goalDescription != null && !goalDescription.equals("")) {
            Characteristic goal = new Characteristic(UUID.randomUUID().toString(), goalDescription);
            insertCharacteristic(goal);

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(TRAINING, training);
        super.onSaveInstanceState(outState);
    }


    public void insertCharacteristic(Characteristic characteristic) {
        characteristicViewModel.insert(characteristic);
    }

}


