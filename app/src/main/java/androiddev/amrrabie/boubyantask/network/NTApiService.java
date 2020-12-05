package androiddev.amrrabie.boubyantask.network;

import androiddev.amrrabie.boubyantask.model.NTResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NTApiService {

    @GET("viewed/{period}.json")
    public Single<NTResponse> getMostPopular(
          @Path("period") int period ,
          @Query("api-key") String apikey
    );
}
