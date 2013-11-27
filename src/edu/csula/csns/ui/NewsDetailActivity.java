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

import edu.csula.csns.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class NewsDetailActivity extends FragmentActivity {

    private NewsPagerFragment pagerFragment;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news_detail );

        getActionBar().setDisplayHomeAsUpEnabled( true );

        FragmentManager fm = getSupportFragmentManager();
        pagerFragment = (NewsPagerFragment) fm.findFragmentById( R.id.container1_news_detail );

        if( pagerFragment == null )
        {
            pagerFragment = new NewsPagerFragment();

            Bundle arguments = new Bundle();
            arguments.putInt( "newsIndex",
                getIntent().getIntExtra( "newsIndex", 0 ) );
            pagerFragment.setArguments( arguments );

            fm.beginTransaction()
                .add( R.id.container1_news_detail, pagerFragment )
                .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch( item.getItemId() )
        {
            case android.R.id.home:
                NavUtils.navigateUpTo( this, new Intent( this,
                    NewsListActivity.class ) );
                return true;
        }
        return super.onOptionsItemSelected( item );
    }

}
