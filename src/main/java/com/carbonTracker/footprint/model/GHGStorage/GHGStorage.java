
public class GHGStorage {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("vehicleTotal")
    private double vehicleTotal;

    @NotBlank
    @JsonProperty("homeTotal")
    private double homeTotal;

    @NotBlank
    @JsonProperty("storageMonth")
    private String storageMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVehicleTotal() {
        return vehicleTotal;
    }

    public void setVehicleTotal(double vehicleTotal) {
        this.vehicleTotal = vehicleTotal;
    }

    public double getHomeTotal() {
        return homeTotal;
    }

    public void setHomeTotal(double homeTotal) {
        this.homeTotal = homeTotal;
    }

    public String getStorageMonth() {
        return storageMonth;
    }

    public void setStorageMonth(String storageMonth) {
        this.storageMonth = storageMonth;
    }

}