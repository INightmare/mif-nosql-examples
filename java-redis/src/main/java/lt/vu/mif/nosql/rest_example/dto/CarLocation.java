package lt.vu.mif.nosql.rest_example.dto;

public class CarLocation {
    private double lat;
    private double lng;

    public CarLocation() {}

    public CarLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    
    
}
