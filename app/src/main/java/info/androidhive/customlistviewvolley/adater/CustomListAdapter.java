package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
    NetworkImageView thumbNail;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Movie> movieItems) {
		this.activity = activity;
		this.movieItems = movieItems;
	}

	@Override
	public int getCount() {
		return movieItems.size();
	}

	@Override
	public Object getItem(int location) {
		return movieItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		 thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		//TextView rating = (TextView) convertView.findViewById(R.id.rating);
		//TextView genre = (TextView) convertView.findViewById(R.id.genre);
		//TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		Movie m = movieItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(movieItems.get(position).getThumbnailUrl(), imageLoader);
		
		// title
		title.setText(m.getTitle());
        Button share=(Button)convertView.findViewById(R.id.sharebutton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  /*  Uri imageUri = Uri.parse(movieItems.get(0).getThumbnailUrl());


                    Intent share = new Intent(Intent.ACTION_SEND
                    );

                    // If you want to share a png image only, you can do:
                    // setType("image/png"); OR for jpeg: setType("image/jpeg");
                    share.setType("image/*");

                    // Make sure you put example png image named myImage.png in your
                    // directory

                    share.putExtra(Intent.EXTRA_STREAM, imageUri);

                  activity.startActivity(Intent.createChooser(share, "Share Image!"));*/
/*
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("image/*");

                    //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                   // sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, movieItems.get(0).getThumbnailUrl());
                sharingIntent.putExtra(Intent.EXTRA_STREAM,movieItems.get(0).getThumbnailUrl());
                   activity. startActivity(Intent.createChooser(sharingIntent, "Share "));
*/
              /* Uri imageUri = Uri.parse(movieItems.get(0).getThumbnailUrl()
                        );


                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
                shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri );
                shareIntent.setType("image/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
               activity. startActivity(Intent.createChooser(shareIntent, "send"));*/
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
               // String shareBody =lifeStreamController.urlValue() ;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, movieItems.get(0).getThumbnailUrl());
                activity. startActivity(Intent.createChooser(sharingIntent, "Share "));

            }
        });

		// rating
		//rating.setText("Rating: " + String.valueOf(m.getRating()));
		
		// genre
		String genreStr = "";
		for (String str : m.getGenre()) {
			genreStr += str + ", ";
		}
		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
				genreStr.length() - 2) : genreStr;
	//	genre.setText(genreStr);
		
		// release year
		//year.setText(String.valueOf(m.getYear()));

		return convertView;
	}

}