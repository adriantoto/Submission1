package dicoding.adrian.madesubmission1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.madesubmission1.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

public class DetailMovieActivity extends AppCompatActivity {

    // Default Value
    public static final String EXTRA_MOVIE = "extra_movie";

    // Data Variables Declaration
    TextView txtTitleDetail;
    TextView txtGenreDetail;
    TextView txtRuntimeDetail;
    TextView txtDirectorDetail;
    TextView txtRatingDetail;
    TextView txtOverviewDetail;
    TextView txtScoreAngkaDetail;
    ImageView posterBanner;
    ImageView posterDetail;

    // Like Button Variable
    LikeButton likeButton;

    // URL
    String urlTrailer;

    // Button Variable Declaration
    ImageButton btnPlay;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        // Translucent Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Casting Data Variables
        txtTitleDetail = findViewById(R.id.txt_title_detail);
        txtGenreDetail = findViewById(R.id.txt_genreDetail);
        txtRuntimeDetail = findViewById(R.id.txt_runtimeDetail);
        txtDirectorDetail = findViewById(R.id.txt_directorDetail);
        txtRatingDetail = findViewById(R.id.txt_ratingDetail);
        txtOverviewDetail = findViewById(R.id.txt_overviewDetail);
        txtScoreAngkaDetail = findViewById(R.id.txt_scoreAngkaDetail);
        posterBanner = findViewById(R.id.poster_banner);
        posterDetail = findViewById(R.id.poster_detail);

        // Casting Like Button Variables
        likeButton = findViewById(R.id.star_button);

        // Casting Button Variables
        btnPlay = findViewById(R.id.btn_play);
        btnBack = findViewById(R.id.btn_back);

        // Menerima intent
        final Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        // Mengisi data String
        txtTitleDetail.setText(movie.getTitle());
        txtGenreDetail.setText(movie.getGenre());
        txtRuntimeDetail.setText(movie.getRuntime());
        txtDirectorDetail.setText(movie.getDirector());
        txtRatingDetail.setText(movie.getRating());
        txtOverviewDetail.setText(movie.getOverview());
        txtScoreAngkaDetail.setText(String.valueOf(movie.getScore()));

        // Mengisi data image
        posterBanner.setImageResource(movie.getPoster());
        posterDetail.setImageResource(movie.getPoster());

        // Mengisi data URL Trailer
        urlTrailer = movie.getTrailer();

        // setOnLikeListener likeButton
        final String forToast = "Anda menyukai " + movie.getTitle();
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(DetailMovieActivity.this, forToast, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });

        // setOnClickListener untuk Button Play
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn(urlTrailer);
            }
        });

        // setOnClickListener untuk Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.no_animation, R.anim.slide_down);
            }
        });

    }

    // Animation onBackPressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailMovieActivity.this.overridePendingTransition(R.anim.no_animation, R.anim.slide_down);
    }

    // Methods clicked_button
    public void clicked_btn(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
