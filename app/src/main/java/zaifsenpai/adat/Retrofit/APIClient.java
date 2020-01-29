package zaifsenpai.adat.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zaifsenpai.adat.Retrofit.Response.RandomPhoto;
import zaifsenpai.adat.Retrofit.Response.User;

public interface APIClient {
    @GET("photos/random")
    Call<RandomPhoto> GetRandomBackground(@Query("client_id") String clientID, @Query("query") String query);
}
