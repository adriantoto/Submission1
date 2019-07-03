package dicoding.adrian.madesubmission1;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    // POJO Properties
    private int poster;
    private String title;
    private String releasedYear;
    private String overview;
    private String rating;
    private String genre;
    private int score;
    private String trailer;
    private String runtime;
    private String director;

    // Constructor with Parameter
    Movie(int poster, String title, String releasedYear, String overview,
          String rating, String genre, int score, String trailer, String runtime, String director) {
        this.poster = poster;
        this.title = title;
        this.releasedYear = releasedYear;
        this.overview = overview;
        this.rating = rating;
        this.genre = genre;
        this.score = score;
        this.trailer = trailer;
        this.runtime = runtime;
        this.director = director;
    }

    // Contructor with no parameter
    Movie() {

    }

    // Getter and Setter

    String getRating() {
        return rating;
    }

    void setRating(String rating) {
        this.rating = rating;
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    int getPoster() {
        return poster;
    }

    void setPoster(int poster) {
        this.poster = poster;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getReleasedYear() {
        return releasedYear;
    }

    void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    String getOverview() {
        return overview;
    }

    void setOverview(String overview) {
        this.overview = overview;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    String getTrailer() {
        return trailer;
    }

    void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    String getRuntime() {
        return runtime;
    }

    void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    String getDirector() {
        return director;
    }

    void setDirector(String director) {
        this.director = director;
    }

    // Parcelable Objects

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.releasedYear);
        dest.writeString(this.overview);
        dest.writeString(this.rating);
        dest.writeString(this.genre);
        dest.writeInt(this.score);
        dest.writeString(this.trailer);
        dest.writeString(this.runtime);
        dest.writeString(this.director);
    }

    private Movie(Parcel in) {
        this.poster = in.readInt();
        this.title = in.readString();
        this.releasedYear = in.readString();
        this.overview = in.readString();
        this.rating = in.readString();
        this.genre = in.readString();
        this.score = in.readInt();
        this.trailer = in.readString();
        this.runtime = in.readString();
        this.director = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
