package dobrowol.swimmerlog.add_characteristic;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import dobrowol.swimmerlog.add_training.Training;
import dobrowol.swimmerlog.add_training.TrainingRoomDatabase;

public class CharacteristicRepository {

    private CharacteristicDAO characteristicDAO;

    CharacteristicRepository(Application application) {
        TrainingRoomDatabase db = TrainingRoomDatabase.getDatabase(application);
        characteristicDAO = db.characteristicDAO();
    }

    public void insert (Characteristic characteristic) {
        new insertAsyncTask(characteristicDAO).execute(characteristic);
    }

    private static class insertAsyncTask extends AsyncTask<Characteristic, Void, Void> {

        private CharacteristicDAO mAsyncTaskDao;

        insertAsyncTask(CharacteristicDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Characteristic... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public LiveData<Characteristic> getCharacteristicWithDescription(String description){
        return characteristicDAO.getCharacteristicWithDescription(description);
    }
}