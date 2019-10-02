package dobrowol.swimmerlog.add_characteristic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dobrowol.swimmerlog.add_training.Training;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TrainingCharacteristicDAO {
    @Query("SELECT * FROM training_characteristic")
    LiveData<List<TrainingCharacteristic>> getAll();

    @Insert(onConflict = REPLACE)
    void insert(TrainingCharacteristic trainingCharacteristic);

    @Delete
    void delete(TrainingCharacteristic trainingCharacteristic);

    @Update
    void update(TrainingCharacteristic trainingCharacteristic);

    @Query("SELECT characteristic_table.* FROM characteristic_table INNER JOIN training_characteristic ON characteristic_table.id=training_characteristic.characteristicId WHERE training_characteristic.trainingId=:trainingId")
    LiveData<List<Characteristic>> getCharacteristicsForTraining(String trainingId);

    @Query("SELECT training_table.* FROM training_table INNER JOIN training_characteristic ON training_table.id=training_characteristic.trainingId WHERE training_characteristic.characteristicId=:characteristicId")
    LiveData<List<Training>> getTrainingsForCharacteristic(String characteristicId);

    @Query("SELECT training_characteristic.id, training_characteristic.trainingId, characteristic_table.description AS characteristicName, training_characteristic.rating" +
            " FROM training_characteristic INNER JOIN characteristic_table ON training_characteristic.characteristicId = characteristic_table.id WHERE trainingId=:trainingId")
    LiveData<List<TrainingCharacteristicResolved>> getTrainingCharacteristicByTrainingId(String trainingId);
}

