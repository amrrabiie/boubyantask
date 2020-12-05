package androiddev.amrrabie.boubyantask.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

import androiddev.amrrabie.boubyantask.R;
import androiddev.amrrabie.boubyantask.adapters.ArticlesAdapter;
import androiddev.amrrabie.boubyantask.databinding.ActivityMainBinding;
import androiddev.amrrabie.boubyantask.di.RetrofitModule;
import androiddev.amrrabie.boubyantask.model.ResultsItem;
import androiddev.amrrabie.boubyantask.utils.Network;
import androiddev.amrrabie.boubyantask.viewmodel.NTViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    
    ActivityMainBinding binding;
    NTViewModel viewModel;
    ArticlesAdapter adapter;
    int priod=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        
        viewModel=new ViewModelProvider(this).get(NTViewModel.class);
        
        adapter=new ArticlesAdapter(this);

        bindPeriodSpinner();
        
        bindArticlesData();
    }

    private void bindPeriodSpinner() {
        String[] periods=new String[]{"1 Day","7 Days","30 Days"};
        ArrayAdapter<String> periodsArray= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, periods);
        periodsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.periodspinner.setAdapter(periodsArray);

        binding.periodspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 String valsel = (String) parent.getItemAtPosition(position);
                 switch (valsel){
                     case "1 Day":
                         priod=1;
                         break;
                     case "7 Days":
                         priod=7;
                         break;
                     case "30 Days":
                         priod=30;
                         break;
                     default:
                         priod=1;
                         break;
                 }
                //Toast.makeText(MainActivity.this, "Days : " + priod, Toast.LENGTH_SHORT).show();
                bindArticlesData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void bindArticlesData() {
        if(Network.isNetworkAvailable(MainActivity.this)){
            bindData();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Warning!");
            builder.setMessage("No network connection avaiable !");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }



    private void bindData() {
        viewModel.getMostPopular(priod, RetrofitModule.Api_key);

        viewModel.getPopularlist().observe(MainActivity.this, new Observer<List<ResultsItem>>() {
            @Override
            public void onChanged(List<ResultsItem> list) {
                binding.articlesrecycler.setAdapter(adapter);
                adapter.setList(list);
                binding.pbar.setVisibility(View.GONE);
                binding.articlesrecycler.setVisibility(View.VISIBLE);
            }
        });
    }


}