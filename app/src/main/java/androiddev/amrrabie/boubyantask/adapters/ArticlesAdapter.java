package androiddev.amrrabie.boubyantask.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadata;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import androiddev.amrrabie.boubyantask.R;
import androiddev.amrrabie.boubyantask.model.MediaItem;
import androiddev.amrrabie.boubyantask.model.MediaMetadataItem;
import androiddev.amrrabie.boubyantask.model.ResultsItem;
import androiddev.amrrabie.boubyantask.ui.DetailsActivity;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> implements View.OnClickListener {

    Context mContext;
    List<ResultsItem> articleslist;

    public ArticlesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<ResultsItem> articleslist){
        this.articleslist=articleslist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticlesAdapter.ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false);
        v.setOnClickListener(this);
        return new ArticlesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ArticlesViewHolder holder, int position) {

        holder.title.setText(articleslist.get(position).getTitle());
        holder.details.setText(articleslist.get(position).getJsonMemberAbstract());

        List<MediaItem> medialist=articleslist.get(position).getMedia();

        if(medialist.size() > 0) {

            MediaItem media = articleslist.get(position).getMedia().get(0);

            if (media != null) {
                List<MediaMetadataItem> metadatalist=media.getMediaMetadata();
                if(metadatalist.size() > 0) {
                    String url = media.getMediaMetadata().get(2).getUrl();
                    if (url != null) {
                        Glide.with(mContext)
                                .load(articleslist.get(position).getMedia().get(0).getMediaMetadata().get(2).getUrl())
                                .into(holder.img);
                    }
                }
            }
        }




        holder.cview.setTag(position);

    }

    @Override
    public int getItemCount() {
        return articleslist.size();
    }

    @Override
    public void onClick(View v) {
        int pos=(int)v.getTag();
        Intent intent=new Intent(mContext, DetailsActivity.class);
        intent.putExtra("art",(Serializable) articleslist.get(pos));
        mContext.startActivity(intent);
        //Toast.makeText(mContext, "Article: " + articleslist.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        ImageView img;
        TextView title,details;
        public ArticlesViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            img=v.findViewById(R.id.img);
            title=v.findViewById(R.id.title);
            details=v.findViewById(R.id.details);
        }
    }
}
