package dobrowol.swimmerlog.add_characteristic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dobrowol.swimmerlog.add_training.Training;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface CharacteristicDAO {
    @Query("SELECT * FROM characteristic_table")
    LiveData<List<Characteristic>> getAll();

    @Insert(onConflict = IGNORE)
    void insert(Characteristic trainingCharacteristic);

    @Delete
    void delete(Characteristic trainingCharacteristic);

    @Update
    void update(Characteristic trainingCharacteristic);

    @Query("SELECT * FROM characteristic_table  WHERE description=:description")
    LiveData<Characteristic> getCharacteristicWithDescription(String description);
}

