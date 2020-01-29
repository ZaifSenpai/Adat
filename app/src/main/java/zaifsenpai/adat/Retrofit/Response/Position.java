
package zaifsenpai.adat.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Position {

    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Position() {
    }

    /**
     * 
     * @param latitude
     * @param longitude
     */
    public Position(Object latitude, Object longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

}
