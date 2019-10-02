package dobrowol.swimmerlog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swimmerlog.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dobrowol.swimmerlog.add_training.Training;
import dobrowol.swimmerlog.add_training.TrainingViewModel;

public class TrainingListFragment extends Fragment implements AllTrainingRecyclerViewAdapter.OnListFragmentInteractionListener {
    private View rootView;
    RecyclerView recyclerView;
    AllTrainingRecyclerViewAdapter adapter;
    List<Training> trainings;
    TrainingViewModel trainingViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.training_list_fragment_layout, container, false);

        recyclerView = rootView.findViewById(R.id.list);
        // Set the adapter

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new AllTrainingRecyclerViewAdapter( this);
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(view -> Navigation.findNavController(rootView).navigate(R.id.action_trainingListFragment_to_characteristicsFragment));

        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel.class);
        trainingViewModel.getAllTrainings().observe(this, trainings1 -> {
            if(!trainings1.isEmpty()){
                trainings = trainings1;
                adapter.setTrainings(trainings);
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }

    @Override
    public void onListFragmentInteraction(Training item) {
        TrainingListFragmentDirections.ActionTrainingListFragmentToCharacteristicsFragment action =
                TrainingListFragmentDirections.actionTrainingListFragmentToCharacteristicsFragment();
        action.setTraining(item);

        Navigation.findNavController(rootView).navigate(action);
    }

    @Override
    public void onDeleteTraining(Training training) {

    }
}
