package dobrowol.swimmerlog.add_characteristic;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import dobrowol.swimmerlog.add_training.Training;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "training_characteristic",
        indices = @Index(value = {"trainingId","characteristicId"}, unique = true),
        foreignKeys = {
        @ForeignKey(onDelete = CASCADE,
                entity = Training.class,
                parentColumns = "id",
                childColumns = "trainingId"),
        @ForeignKey(onDelete = CASCADE,
                entity = Characteristic.class,
                parentColumns = "id",
                childColumns = "characteristicId")
})
public class TrainingCharacteristic {
    @PrimaryKey
    @NonNull
    String id;
    String trainingId;
    public String characteristicId;
    public Integer rating;

    public TrainingCharacteristic(@NotNull String id, String trainingId, String characteristicId, int rating) {
        this.id = id;
        this.trainingId = trainingId;
        this.characteristicId = characteristicId;
        this.rating = rating;
    }
}
