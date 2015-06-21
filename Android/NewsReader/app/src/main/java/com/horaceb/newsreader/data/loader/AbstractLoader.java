package com.horaceb.newsreader.data.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by HoraceBG on 13/06/2015.
 */
public abstract class AbstractLoader<T> extends AsyncTaskLoader<List<T>> {

    private List<T> items;

    public AbstractLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (items != null) {
            deliverResult(items);
        }

        if (takeContentChanged() || items == null) {
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
    public void onCanceled(List<T> items) {
        super.onCanceled(items);
    }

    @Override
    public void deliverResult(List<T> data) {
        if (isReset()) {
            // The Loader has been reset; ignore the result and invalidate the data.
            releaseResources(data);
            return;
        }

        List<T> oldData = items;
        items = data;

        if (isStarted()) {
            // If the Loader is in a started state, deliver the results to the
            // client. The superclass method does this for us.
            super.deliverResult(data);
        }

        if (oldData != null && oldData != data) {
            releaseResources(oldData);
        }

    }

    private void releaseResources(List<T> data) {
        // For a simple List, there is nothing to do. For something like a Cursor, we
        // would close it in this method. All resources associated with the Loader
        // should be released here.
    }

}

