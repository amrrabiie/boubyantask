package androiddev.amrrabie.boubyantask.repostry;

import javax.inject.Inject;

import androiddev.amrrabie.boubyantask.model.NTResponse;
import androiddev.amrrabie.boubyantask.network.NTApiService;
import io.reactivex.rxjava3.core.Single;

public class NTRepostry {
    private NTApiService ntApiService;

    @Inject
    public NTRepostry(NTApiService ntApiService) {
        this.ntApiService = ntApiService;
    }

    public Single<NTResponse> getMostPopular(int period ,String apikey){
        return ntApiService.getMostPopular(period,apikey);
    }
}
