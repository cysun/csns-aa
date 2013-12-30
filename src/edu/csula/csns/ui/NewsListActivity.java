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
import edu.csula.csns.model.DepartmentData;
import edu.csula.csns.model.UserData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class NewsListActivity extends FragmentActivity implements
    NewsListFragment.Callbacks, ViewPager.OnPageChangeListener {

    private boolean isTwoPane = false;

    private NewsListFragment listFragment;

    private NewsPagerFragment pagerFragment;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news_list );

        FragmentManager fm = getSupportFragmentManager();
        listFragment = (NewsListFragment) fm.findFragmentById( R.id.fragment_news_list );

        if( findViewById( R.id.container2_news_detail ) != null )
        {
            isTwoPane = true;

            pagerFragment = (NewsPagerFragment) fm.findFragmentById( R.id.container2_news_detail );
            if( pagerFragment == null )
            {
                pagerFragment = new NewsPagerFragment();
                fm.beginTransaction()
                    .add( R.id.container2_news_detail, pagerFragment )
                    .commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        super.onCreateOptionsMenu( menu );
        getMenuInflater().inflate( R.menu.news, menu );
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu( Menu menu )
    {
        super.onPrepareOptionsMenu( menu );
        if( UserData.getInstance( getApplicationContext() ).getUser() == null )
            menu.findItem( R.id.menu_logout ).setVisible( false );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch( item.getItemId() )
        {
            case R.id.menu_refresh:
                listFragment.loadNews( true );
                return true;

            case R.id.menu_logout:
                DepartmentData.getInstance( getApplicationContext() ).clear();
                UserData.getInstance( getApplicationContext() ).clear();
                startActivity( new Intent( getApplicationContext(),
                    EntryActivity.class ) );
                finish();

            default:
                return super.onOptionsItemSelected( item );
        }
    }

    /* NewsListFragment.Callbacks */

    @Override
    public void onDataLoaded( int initialPosition )
    {
        ViewPager viewPager = pagerFragment.getViewPager();
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setCurrentItem( initialPosition, false );
    }

    @Override
    public void onItemSelected( int position )
    {
        if( isTwoPane )
        {
            pagerFragment.getViewPager().setCurrentItem( position, false );
        }
        else
        {
            Intent intent = new Intent( this, NewsDetailActivity.class );
            intent.putExtra( "newsIndex", position );
            startActivity( intent );
        }
    }

    @Override
    public boolean isTwoPane()
    {
        return isTwoPane;
    }

    /* ViewPager.OnPageChangeListener */

    @Override
    public void onPageScrollStateChanged( int state )
    {
    }

    @Override
    public void onPageScrolled( int position, float positionOffset,
        int positionOffsetPixels )
    {
    }

    @Override
    public void onPageSelected( int position )
    {
        listFragment.getListView().setItemChecked( position, true );
    }

}
