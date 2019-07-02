package dicoding.adrian.madesubmission1;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.app.madesubmission1.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variable back pressed
    private long backPressedTime;
    private Toast backToast;

    // Item Data Variables Declaration
    private String[] dataTitle;
    private String[] dataReleasedYear;
    private String[] dataOverview;
    private String[] dataRating;
    private String[] dataGenre;
    private int[] dataScore;
    private String[] dataTrailer;
    private String[] dataRuntime;
    private String[] dataDirector;
    private TypedArray dataPoster;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar Gradient
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient));
        }

        // Background Layout Gradient
        FrameLayout frameLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) frameLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        // Memanggil method prepare
        prepare();

        // adapter instance
        adapter = new MovieAdapter(this, getMovies());

        // Cast and then set ListView and EmptyView
        ListView listView = findViewById(android.R.id.list);
        listView.setEmptyView(findViewById(android.R.id.empty));

        // Set Adapter
        listView.setAdapter(adapter);
    }

    // onBackPressed untuk exit
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    // onCreateOptionsMenu untuk SearchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    // METHODS

    private ArrayList<Movie> getMovies() {
        movies = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPoster(dataPoster.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setOverview(dataOverview[i]);
            movie.setRating(dataRating[i]);
            movie.setGenre(dataGenre[i]);
            movie.setReleasedYear(dataReleasedYear[i]);
            movie.setScore(dataScore[i]);
            movie.setTrailer(dataTrailer[i]);
            movie.setRuntime(dataRuntime[i]);
            movie.setDirector(dataDirector[i]);
            movies.add(movie);
        }
        return movies;
    }

    private void prepare() {
        dataPoster = getResources().obtainTypedArray(R.array.data_poster);
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataOverview = getResources().getStringArray(R.array.data_overview);
        dataRating = getResources().getStringArray(R.array.data_rating);
        dataGenre = getResources().getStringArray(R.array.data_genre);
        dataReleasedYear = getResources().getStringArray(R.array.data_released_year);
        dataScore = getResources().getIntArray(R.array.data_score);
        dataTrailer = getResources().getStringArray(R.array.data_trailer);
        dataRuntime = getResources().getStringArray(R.array.data_runtime);
        dataDirector = getResources().getStringArray(R.array.data_director);
    }
}
