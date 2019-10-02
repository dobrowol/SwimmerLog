package dobrowol.swimmerlog.add_characteristic;

public class TrainingCharacteristicResolved {
    private String id;
    private String trainingId;
    public String characteristicName;
    public Integer rating;

    TrainingCharacteristicResolved(String id, String trainingId, String characteristicName, int rating) {
        this.id = id;
        this.trainingId = trainingId;
        this.characteristicName = characteristicName;
        this.rating = rating;
    }
}
