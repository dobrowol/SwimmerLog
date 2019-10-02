package dobrowol.swimmerlog.add_characteristic;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName="characteristic_table",
indices = @Index(value = {"description"}, unique = true))
public class Characteristic {
    @PrimaryKey
    @NonNull
    public String id;

    public String description;
    public Characteristic(@NotNull String id, String description) {
        this.id = id;
        this.description = description;
    }
}
