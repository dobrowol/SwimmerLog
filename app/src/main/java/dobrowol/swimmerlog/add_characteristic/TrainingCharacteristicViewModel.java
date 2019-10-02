package dobrowol.swimmerlog.add_characteristic;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dobrowol.swimmerlog.add_training.Training;

public class TrainingCharacteristicViewModel extends AndroidViewModel {

    private TrainingCharacteristicRepository mRepository;

    public LiveData<List<TrainingCharacteristicResolved>> getTrainingCharacteristicByTrainingId(String trainingId){
        return mRepository.getTrainingCharacteristicsForTraining(trainingId); }

    public TrainingCharacteristicViewModel(Application application) {
        super(application);
        mRepository = new TrainingCharacteristicRepository(application);
    }

    public LiveData<List<Training>> getAllTrainingsForCharacteristic(String characteristicId) {
        return mRepository.getAllTrainingsForCharacteristic(characteristicId); }

    public void insert(TrainingCharacteristic trainingCharacteristic) { mRepository.insert(trainingCharacteristic); }
}

