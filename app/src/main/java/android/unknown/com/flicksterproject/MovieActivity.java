package android.unknown.com.flicksterproject;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.unknown.com.flicksterproject.adapters.MovieArrayAdapter;
import android.unknown.com.flicksterproject.models.Movie;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;
    String url = " https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    //For parsing a JSON object, we will create an object of class JSONObject
    // and specify a string containing JSON data to it.
    AsyncHttpClient client = new AsyncHttpClient();
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MESSAGE WHEN APP STARTS
        Toast.makeText(getApplication(), "Swipe Down To Get Started", Toast.LENGTH_LONG).show();
        //LIST VIEW TO STORE MOVIES
        lvItems = (ListView) findViewById(R.id.lvMovies);
        //ARRAYLIST TO STORE MOVIE OBJECTS
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);
        //URL Of API REQUEST

        /*Using the AsyncTask is the easiest and most convenient way
         to manage background tasks from within an Activity. */
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        client.get(url, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResult = null;
                try {
                    movieAdapter.clear();
                    //returns json objects
                    movieJsonResult = response.getJSONArray("results");
                    //converts objects to arraylist items
                    movies.addAll(Movie.fromJSONArray(movieJsonResult));
                    movieAdapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });

    }

}

