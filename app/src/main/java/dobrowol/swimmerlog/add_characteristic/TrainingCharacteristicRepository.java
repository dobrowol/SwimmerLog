package dobrowol.swimmerlog.add_characteristic;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import java.util.List;

import dobrowol.swimmerlog.add_training.Training;
import dobrowol.swimmerlog.add_training.TrainingRoomDatabase;

public class TrainingCharacteristicRepository {

    private TrainingCharacteristicDAO trainingCharacteristicDAO;

    TrainingCharacteristicRepository(Application application) {
        TrainingRoomDatabase db = TrainingRoomDatabase.getDatabase(application);
        trainingCharacteristicDAO = db.trainingCharacteristicDAO();
    }

    LiveData<List<Characteristic>> getCharacteristicsForTraining(String trainingId) {
        return trainingCharacteristicDAO.getCharacteristicsForTraining(trainingId);
    }

    LiveData<List<Training>> getAllTrainingsForCharacteristic(String characteristicId) {
        return trainingCharacteristicDAO.getTrainingsForCharacteristic(characteristicId);
    }

    public void insert (TrainingCharacteristic trainingCharacteristic) {
        new insertAsyncTask(trainingCharacteristicDAO).execute(trainingCharacteristic);
    }

    public LiveData<List<TrainingCharacteristicResolved>> getTrainingCharacteristicsForTraining(String trainingId) {
        return trainingCharacteristicDAO.getTrainingCharacteristicByTrainingId(trainingId);
    }

    private static class insertAsyncTask extends AsyncTask<TrainingCharacteristic, Void, Void> {

        private TrainingCharacteristicDAO mAsyncTaskDao;

        insertAsyncTask(TrainingCharacteristicDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TrainingCharacteristic... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}