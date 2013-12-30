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

    private int activatedPosition = ListView.INVALID_POSITION;

    public NewsListFragment()
    {
    }

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

    /*
     * In the getNewsTask we need to access both the parent activity and the
     * ListView, so the proper place to do that is in onAcitivityCreated(),
     * which is called after onCreate() and onCreateView().
     */
    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );

        if( savedInstanceState != null
            && savedInstanceState.containsKey( "activatedPosition" ) )
            activatedPosition = savedInstanceState.getInt( "activatedPosition" );

        loadNews( false );
    }

    /*
     * ListView automatically saves/restored the selected position, but not the
     * activated position ("selected" and "activated" are different), so we have
     * to it ourselves.
     */
    @Override
    public void onSaveInstanceState( Bundle outState )
    {
        super.onSaveInstanceState( outState );

        activatedPosition = getListView().getCheckedItemPosition();
        if( activatedPosition != ListView.INVALID_POSITION )
            outState.putInt( "activatedPosition", activatedPosition );
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        callbacks = dummyCallbacks;
    }

    @Override
    public void onListItemClick( ListView listView, View view, int position,
        long id )
    {
        callbacks.onItemSelected( position );
    }

    public void loadNews( boolean forceUpdate )
    {
        (new GetNewsTask()).execute( forceUpdate );
    }

    private class GetNewsTask extends AsyncTask<Boolean, Void, Boolean> {

        @Override
        protected Boolean doInBackground( Boolean... params )
        {
            boolean forceUpdate = params.length > 0 ? params[0] : false;
            return NewsData.getInstance( getActivity() ).update( forceUpdate );
        }

        @Override
        protected void onPostExecute( Boolean updated )
        {
            if( updated || getListAdapter() == null )
            {
                List<News> newses = NewsData.getInstance( getActivity() )
                    .getNewses();
                setListAdapter( new NewsListAdapter( getActivity(), newses ) );

                if( callbacks.isTwoPane() )
                {
                    getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );

                    if( activatedPosition == ListView.INVALID_POSITION
                        && newses != null && newses.size() > 0 )
                        activatedPosition = 0;

                    getListView().setItemChecked( activatedPosition, true );
                    callbacks.onDataLoaded( activatedPosition );
                }
            }
        }
    }

    /*
     * Callbacks are the ways to notify the activity containing this fragment
     * about events happened in this fragment. A dummy implementation of
     * Callbacks is provided for the case when the fragment is not attached to
     * any activity.
     */

    public interface Callbacks {

        public void onDataLoaded( int initialPosition );

        public void onItemSelected( int position );

        public boolean isTwoPane();

    }

    private static Callbacks dummyCallbacks = new Callbacks() {

        @Override
        public void onDataLoaded( int initialPosition )
        {
        }

        @Override
        public void onItemSelected( int position )
        {
        }

        @Override
        public boolean isTwoPane()
        {
            return false;
        }

    };

}
