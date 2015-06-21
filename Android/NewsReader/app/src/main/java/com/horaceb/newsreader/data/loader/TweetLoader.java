package com.horaceb.newsreader.data.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Base64;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.horaceb.newsreader.data.model.Tweet;
import com.horaceb.newsreader.data.model.TwitterTokenType;
import com.horaceb.newsreader.data.requests.TwitterApiService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by HoraceBG on 13/06/2015.
 */
public class TweetLoader extends AsyncTaskLoader<List<Tweet>> implements Callback<TwitterTokenType> {

    private List<Tweet> tweets;
    private final String screenName;
    private String twitterToken;

    public TweetLoader(Context context, final String screenName) {
        super(context);
        this.screenName = screenName;
    }

    @Override
    public List<Tweet> loadInBackground() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(TwitterApiService.API_URL)
                .build();

        TwitterApiService service = restAdapter.create(TwitterApiService.class);
        service.getToken("Basic " + generateToken(), "client_credentials", this);
        return null;
    }

    private String generateToken() {
        String token = "";
        try {
            final String urlConsumerKey = URLEncoder.encode(TwitterApiService.CONSUMER_KEY, "UTF-8");
            final String urlConsumerSecret = URLEncoder.encode(TwitterApiService.CONSUMER_SECRET, "UTF-8");
            final String combined = urlConsumerKey.concat(":").concat(urlConsumerSecret);

            token = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    protected void onStartLoading() {
        if (tweets != null) {
            deliverResult(tweets);
        }

        if (takeContentChanged() || tweets == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    @Override
    public void onCanceled(List<Tweet> data) {
        super.onCanceled(data);
    }

    @Override
    public void deliverResult(List<Tweet> data) {
        if (isReset()) {
            // The Loader has been reset; ignore the result and invalidate the data.
            releaseResources(data);
            return;
        }

        List<Tweet> oldData = tweets;
        tweets = data;

        if (isStarted()) {
            // If the Loader is in a started state, deliver the results to the
            // client. The superclass method does this for us.
            super.deliverResult(data);
        }

        if (oldData != null && oldData != data) {
            releaseResources(oldData);
        }

    }

    private void releaseResources(List<Tweet> data) {
        // For a simple List, there is nothing to do. For something like a Cursor, we
        // would close it in this method. All resources associated with the Loader
        // should be released here.
    }

    @Override
    public void success(TwitterTokenType twitterTokenType, Response response) {
        Toast.makeText(getContext(), twitterTokenType.accessToken, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

