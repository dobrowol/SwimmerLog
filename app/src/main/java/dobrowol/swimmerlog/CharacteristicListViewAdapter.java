package dobrowol.swimmerlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.swimmerlog.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dobrowol.swimmerlog.add_characteristic.TrainingCharacteristicResolved;
import dobrowol.swimmerlog.add_training.Training;

public class CharacteristicListViewAdapter extends RecyclerView.Adapter<CharacteristicListViewAdapter.CustomViewHolder> {

    Training training;
    private List<TrainingCharacteristicResolved> trainingCharacteristics;

    public interface OnItemClickListener{

        void onRatingBarChange(String trainingId, String goal, float rating);
    }

    private ArrayList<String> dataList;
    private  OnItemClickListener listener;


    void setTrainingCharacteristics(List<TrainingCharacteristicResolved> trainingCharacteristics){
        this.trainingCharacteristics = trainingCharacteristics;
    }

    public CharacteristicListViewAdapter(ArrayList<String> data, OnItemClickListener listener)
    {
        this.dataList = data;
        this.listener = listener;
    }

    void setTraining(Training training){
        this.training = training;
    }
    @NotNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.characteristic_layout, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position)
    {
        holder.textViewText.setText(dataList.get(position));
        if(trainingCharacteristics != null) {
            for (TrainingCharacteristicResolved trainingCharacteristic : trainingCharacteristics) {
                if (trainingCharacteristic.characteristicName.equals(dataList.get(position))) {
                    holder.fillViewWithRating(trainingCharacteristic.rating);
                }
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewText;
        RatingBar ratingBar;

        public CustomViewHolder(View itemView)
        {
            super(itemView);
            this.textViewText = itemView.findViewById(R.id.characteristicTextView);
            this.ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
                if (fromUser) {
                    listener.onRatingBarChange(training.id, dataList.get(getLayoutPosition()), rating);
                }
            });
        }

        public void fillViewWithRating(Integer rating) {
            ratingBar.setRating(rating);
        }
    }
}

