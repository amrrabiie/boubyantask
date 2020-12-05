package androiddev.amrrabie.boubyantask.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import java.util.List;

import androiddev.amrrabie.boubyantask.R;
import androiddev.amrrabie.boubyantask.databinding.ActivityDetailsBinding;
import androiddev.amrrabie.boubyantask.model.MediaItem;
import androiddev.amrrabie.boubyantask.model.MediaMetadataItem;
import androiddev.amrrabie.boubyantask.model.ResultsItem;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    ResultsItem article;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_details);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_details);

        Intent intent=getIntent();
        if(intent.hasExtra("art")){
            article=(ResultsItem) intent.getSerializableExtra("art");
            bindDetails();
        }
    }

    private void bindDetails() {
        binding.title.setText(article.getTitle());
        binding.details.setText(article.getJsonMemberAbstract());

        List<MediaItem> medialist=article.getMedia();

        if(medialist != null) {

            MediaItem media = article.getMedia().get(0);

            if (media != null) {
                List<MediaMetadataItem> metadatalist=media.getMediaMetadata();
                if(metadatalist != null) {
                    String url = media.getMediaMetadata().get(2).getUrl();
                    if (url != null) {
                        Glide.with(DetailsActivity.this)
                                .load(article.getMedia().get(0).getMediaMetadata().get(2).getUrl())
                                .into(binding.img);
                    }
                }
            }
        }
    }
}