package dobrowol.swimmerlog.add_training;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "training_table")
public class Training implements Serializable {
    @PrimaryKey
    @NonNull
    public String id;
    public Date date;
}
