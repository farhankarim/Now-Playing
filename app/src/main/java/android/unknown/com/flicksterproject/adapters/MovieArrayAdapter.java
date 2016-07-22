package android.unknown.com.flicksterproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.unknown.com.flicksterproject.MovieDescriptionActivity;
import android.unknown.com.flicksterproject.R;
import android.unknown.com.flicksterproject.models.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.buttonDescription = (Button) convertView.findViewById(R.id.bviewMore);
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            //FOR SECOND ACTIVITY
            /*TextView releaseDate;
            TextView id;
            TextView description;
            TextView popularity;
            TextView voteCount;
            TextView voteAverage;*/

            viewHolder.description = (TextView) convertView.findViewById(R.id.tvDESCRIPTION);
            viewHolder.releaseDate = (TextView) convertView.findViewById(R.id.tvRELEASEDATE);
            viewHolder.id = (TextView) convertView.findViewById(R.id.tvID);
            viewHolder.popularity = (TextView) convertView.findViewById(R.id.tvPOPULARITY);
            viewHolder.voteAverage = (TextView) convertView.findViewById(R.id.tvVOTEAVERAGE);
            viewHolder.voteCount = (TextView) convertView.findViewById(R.id.tvVOTECOUNT);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());
        // Show progress bar
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        // Hide progress bar on successful load

        viewHolder.buttonDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MovieDescriptionActivity.class);
                i.putExtra("overview", movie.getOverview());
                i.putExtra("releaseDate", movie.getReleaseDate());
                i.putExtra("id", movie.getId());
                i.putExtra("popularity", movie.getPopularity());
                i.putExtra("voteAverage", movie.getVoteAverage());
                i.putExtra("voteCount", movie.getVoteCount());
                getContext().startActivity(i);
            }
        });


        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {

            Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(20, 20)).into(viewHolder.image, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    if (viewHolder.progressBar != null) {
                        viewHolder.progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onError() {

                }
            });
        } else {
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20, 20)).into(viewHolder.image, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    if (viewHolder.progressBar != null) {
                        viewHolder.progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onError() {

                }
            });
        }
        return convertView;
    }

    // View lookup cache
    public static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView image;
        Button buttonDescription;
        TextView releaseDate;
        TextView description;
        TextView id;
        TextView popularity;
        TextView voteCount;
        TextView voteAverage;
        ProgressBar progressBar;
    }
}
