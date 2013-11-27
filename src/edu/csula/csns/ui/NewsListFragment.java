/*
 * This file is part of the CSNetwork Services Android App (CSNS-AA) project.
 * 
 * Copyright 2013, Chengyu Sun (csun@calstatela.edu).
 * 
 * CSNS-AA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * CSNS-AA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with CSNS-AA. If not, see http://www.gnu.org/licenses/gpl.html.
 */
package edu.csula.csns.ui;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import edu.csula.csns.model.News;
import edu.csula.csns.model.NewsData;
import edu.csula.csns.ui.adapter.NewsListAdapter;

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
        // background selector can check for android:state_selected
        view.setSelected( true );

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
                setListAdapter( new NewsListAdapter( getActivity(), newses ) );
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

        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );

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

        public void onDataLoaded();

        public void onItemSelected( int newsIndex );

    }

    private static Callbacks dummyCallbacks = new Callbacks() {

        @Override
        public void onDataLoaded()
        {
        }

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
