package android.unknown.com.flicksterproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MovieDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        Intent intent = getIntent();

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String overview = intent.getStringExtra("overview");
        String releaseDate = intent.getStringExtra("releaseDate");
        String id = intent.getStringExtra("id");
        String popularity = intent.getStringExtra("popularity");
        String voteAverage = intent.getStringExtra("voteAverage");
        String voteCount = intent.getStringExtra("voteCount");

        TextView tvOVERVIEW = (TextView) findViewById(R.id.tvDESCRIPTION);
        TextView tvRELEASEDATE = (TextView) findViewById(R.id.tvRELEASEDATE);
        TextView tvID = (TextView) findViewById(R.id.tvID);
        TextView tvPOPULARITY = (TextView) findViewById(R.id.tvPOPULARITY);
        TextView tvVOTEAVERAGE = (TextView) findViewById(R.id.tvVOTEAVERAGE);
        TextView tvVOTECOUNT = (TextView) findViewById(R.id.tvVOTECOUNT);


        tvOVERVIEW.setText(overview);
        tvRELEASEDATE.setText(releaseDate);
        tvID.setText(id);
        tvPOPULARITY.setText(popularity);
        tvVOTEAVERAGE.setText(voteAverage);
        tvVOTECOUNT.setText(voteCount);


    }
}
