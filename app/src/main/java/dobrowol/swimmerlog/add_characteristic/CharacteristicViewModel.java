package dobrowol.swimmerlog.add_characteristic;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class CharacteristicViewModel extends AndroidViewModel {

    private CharacteristicRepository mRepository;

    private MutableLiveData<String> query  = new MutableLiveData<>();
    public LiveData<Characteristic> characteristicWithDescription = Transformations.switchMap(query,
            description ->
                    mRepository.getCharacteristicWithDescription(description)
    );
    public void getCharacteristicWithDescription( String description){  query.setValue(description);}

    public CharacteristicViewModel(Application application) {
        super(application);
        mRepository = new CharacteristicRepository(application);
    }


    public void insert(Characteristic characteristic) { mRepository.insert(characteristic); }
}

