package edu.csula.csns.ui;

import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.csula.csns.model.News;
import edu.csula.csns.model.NewsData;

public class NewsListFragment extends ListFragment {

    private Callbacks callbacks = dummyCallbacks;

    public NewsListFragment()
    {
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        (new GetNewsTask()).execute();
    }

    @Override
    public void onListItemClick( ListView listView, View view, int position,
        long id )
    {
        callbacks.onItemSelected( position );
    }

    private class GetNewsTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground( Void... params )
        {
            return NewsData.getInstance( getActivity() ).update();
        }

        @Override
        protected void onPostExecute( Boolean updated )
        {
            if( updated || getListAdapter() == null )
            {
                List<News> newses = NewsData.getInstance( getActivity() )
                    .getNewses();
                setListAdapter( new ArrayAdapter<News>( getActivity(),
                    android.R.layout.simple_list_item_activated_1,
                    android.R.id.text1, newses ) );
            }
        }
    }

    /*
     * Save and restore activated position. This is only useful in the two-pane
     * mode when both the list view and the details view are shown.
     */

    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private int activatedPosition = ListView.INVALID_POSITION;

    @Override
    public void onSaveInstanceState( Bundle outState )
    {
        super.onSaveInstanceState( outState );

        if( activatedPosition != ListView.INVALID_POSITION )
            outState.putInt( STATE_ACTIVATED_POSITION, activatedPosition );
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState )
    {
        super.onViewCreated( view, savedInstanceState );

        if( savedInstanceState != null
            && savedInstanceState.containsKey( STATE_ACTIVATED_POSITION ) )
        {
            setActivatedPosition( savedInstanceState.getInt( STATE_ACTIVATED_POSITION ) );
        }
    }

    private void setActivatedPosition( int position )
    {
        if( position == ListView.INVALID_POSITION )
            getListView().setItemChecked( activatedPosition, false );
        else
            getListView().setItemChecked( position, true );

        activatedPosition = position;
    }

    /*
     * Callbacks are the ways to notify the activity containing this fragment
     * about events happened in this fragment. A dummy implementation of
     * Callbacks is provided for the case when the fragment is not attached to
     * any activity.
     */

    public interface Callbacks {

        public void onItemSelected( int newsIndex );

    }

    private static Callbacks dummyCallbacks = new Callbacks() {

        @Override
        public void onItemSelected( int newsIndex )
        {
        }
    };

    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach( activity );

        if( !(activity instanceof Callbacks) )
        {
            throw new IllegalStateException(
                "Activity must implement fragment's callbacks." );
        }

        callbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        callbacks = dummyCallbacks;
    }

}
