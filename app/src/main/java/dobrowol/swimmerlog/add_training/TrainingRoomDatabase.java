package dobrowol.swimmerlog.add_training;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import dobrowol.swimmerlog.add_characteristic.Characteristic;
import dobrowol.swimmerlog.add_characteristic.CharacteristicDAO;
import dobrowol.swimmerlog.add_characteristic.TrainingCharacteristic;
import dobrowol.swimmerlog.add_characteristic.TrainingCharacteristicDAO;
import dobrowol.swimmerlog.util.Converters;


@Database(entities = {Training.class, Characteristic.class, TrainingCharacteristic.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class TrainingRoomDatabase extends RoomDatabase {

    public abstract TrainingDAO trainingDAO();


    private static volatile TrainingRoomDatabase INSTANCE;

    public static TrainingRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TrainingRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TrainingRoomDatabase.class, "training_database")
                            .fallbackToDestructiveMigration()
                            //.addMigrations(MIGRATION_20_21)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static final Migration MIGRATION_20_21 = new Migration(20, 21) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };


    public abstract TrainingCharacteristicDAO trainingCharacteristicDAO();

    public abstract CharacteristicDAO characteristicDAO();
}




