package android.unknown.com.flicksterproject.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by UNKNOWN on 16-Jul-16.
 */
public class Movie {
    private String posterPath;
    private String originalTitle;
    private String overview;
    private String backdropImage;
    private String releaseDate;
    private String id;
    private String popularity;
    private String voteCount;
    private String voteAverage;


    //CONSTRUCTOR TO CALL JSON DATA AND ASSIGN TO VARIABLE
    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropImage = jsonObject.getString("backdrop_path");
        this.releaseDate = jsonObject.getString("release_date");
        this.id = jsonObject.getString("id");
        this.popularity = jsonObject.getString("popularity");
        this.voteCount = jsonObject.getString("vote_count");
        this.voteAverage = jsonObject.getString("vote_average");
    }

    //iterate to each json array objects
    // and convert to movie object
    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }


    /*
    *
    * GETTERS AND SETTERS
    *
    * */
    public String getBackdropImage() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropImage);
    }

    public void setBackdropImage(String backdropImage) {
        this.backdropImage = backdropImage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        //sets the poster size  342px wide
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }


}
