package androiddev.amrrabie.boubyantask.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import androiddev.amrrabie.boubyantask.model.NTResponse;
import androiddev.amrrabie.boubyantask.model.ResultsItem;
import androiddev.amrrabie.boubyantask.repostry.NTRepostry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NTViewModel extends ViewModel {

    private NTRepostry repostry;
    MutableLiveData<List<ResultsItem>> popularlist=new MutableLiveData<>();

    @ViewModelInject
    public NTViewModel(NTRepostry repostry) {
        this.repostry = repostry;
    }

    public MutableLiveData<List<ResultsItem>> getPopularlist() {
        return popularlist;
    }

    public void getMostPopular(int period,String apikey){
        repostry.getMostPopular(period,apikey)
                .subscribeOn(Schedulers.io())
                .map(new Function<NTResponse, List<ResultsItem>>() {
                    @Override
                    public List<ResultsItem> apply(NTResponse ntResponse) throws Throwable {
                        List<ResultsItem> list=ntResponse.getResults();
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->popularlist.setValue(result),error-> Log.e("error",error.getMessage()));
    }
}
