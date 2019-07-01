package dicoding.adrian.madesubmission1;

public class Movie {
    private int poster;
    private String title;
    private String releasedYear;
    private String overview;
    private String rating;
    private String genre;
    private int score;

    public Movie(int poster, String title, String releasedYear, String overview, String rating, String genre, int score) {
        this.poster = poster;
        this.title = title;
        this.releasedYear = releasedYear;
        this.overview = overview;
        this.rating = rating;
        this.genre = genre;
        this.score = score;
    }

    public Movie() {

    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
