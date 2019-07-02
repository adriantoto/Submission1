package dicoding.adrian.madesubmission1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.madesubmission1.R;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter implements Filterable {

    // Variables Declaration
    private final Context context;
    private ArrayList<Movie> movies;
    private CustomFilter filter; //test
    private ArrayList<Movie> filterList; //test

    // MovieAdapter Constructor with parameter
    MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
        this.filterList = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        // LayoutInflater Declaration
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }

        // ViewHolder Instance
        ViewHolder viewHolder = new ViewHolder(view);

        // Movie Instance
        final Movie movie = (Movie) getItem(i);

        // Binding Process
        viewHolder.bind(movie);

        // |---------INTENT---------|
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v) {

                // Define and start intent
                Intent moveWithObjectIntent = new Intent(context, DetailMovieActivity.class);
                moveWithObjectIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
                context.startActivity(moveWithObjectIntent);

                // Intent Transition Animation
                ((Activity) context).overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
            }
        });

        // Animation
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        view.startAnimation(animation);

        // Output
        return view;
    }

    // GET FILTER
    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList, this);
        }
        return filter;
    }

    // INNER CLASS untuk Custom SearchView
    class CustomFilter extends Filter {

        // Variables Declaration
        MovieAdapter adapter;
        ArrayList<Movie> filterList;

        // CustomFilter Constructor with Parameter
        private CustomFilter(ArrayList<Movie> filterList, MovieAdapter adapter) {
            this.filterList = filterList;
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // CHECK CONSTRAINT VALIDITY
            if (constraint != null && constraint.length() > 0) {
                // CHANGE TO UPPER
                constraint = constraint.toString().toUpperCase().trim();
                ArrayList<Movie> filters = new ArrayList<>();
                // STORE OUR FILTERED ITEMS
                for (int i = 0; i < filterList.size(); i++) {
                    // CHECK
                    if (filterList.get(i).getTitle().toUpperCase().contains(constraint)) {
                        Movie m = new Movie(filterList.get(i).getPoster(), filterList.get(i).getTitle(), filterList.get(i).getReleasedYear(),
                                filterList.get(i).getOverview(), filterList.get(i).getRating(), filterList.get(i).getGenre(),
                                filterList.get(i).getScore(), filterList.get(i).getTrailer(), filterList.get(i).getRuntime(),
                                filterList.get(i).getDirector());
                        // ADD ITEM TO FILTERED ITEMS
                        filters.add(m);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.movies = (ArrayList<Movie>) results.values;
            // REFRESH
            adapter.notifyDataSetChanged();
        }
    }

    // Class ViewHolder
    private class ViewHolder implements View.OnClickListener {
        private TextView txtTitle;
        private TextView txtOverview;
        private TextView txtReleasedYear;
        private TextView txtRatingHome;
        private TextView txtGenreHome;
        private RatingBar scoreHome;
        private ImageView imgPoster;
        private TextView txtScoreAngka;

        // ItemClickListener Declaration
        ItemClickListener itemClickListener;

        ViewHolder(View view) {
            txtTitle = view.findViewById(R.id.txt_title);
            txtOverview = view.findViewById(R.id.txt_overview);
            txtReleasedYear = view.findViewById(R.id.txt_releasedYear);
            txtRatingHome = view.findViewById(R.id.txt_ratingHome);
            txtGenreHome = view.findViewById(R.id.txt_genreHome);
            scoreHome = view.findViewById(R.id.scoreHome);
            imgPoster = view.findViewById(R.id.img_poster);
            txtScoreAngka = view.findViewById(R.id.txt_scoreAngkaHome);

            // SetOnClick
            view.setOnClickListener(this);
        }

        void bind(Movie movie) {
            txtTitle.setText(movie.getTitle());
            txtReleasedYear.setText(movie.getReleasedYear());
            txtOverview.setText(movie.getOverview());
            txtRatingHome.setText(movie.getRating());
            txtGenreHome.setText(movie.getGenre());
            imgPoster.setImageResource(movie.getPoster());
            scoreHome.setRating((float) ((movie.getScore() * 5) / 100));
            txtScoreAngka.setText(String.valueOf(movie.getScore()));
        }

        // onClick (karena implement oClickListener)
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v);
        }

        // Method setItemClickListener
        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }

    }
}
