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
import android.support.v4.view.ViewPager;

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

        if( findViewById( R.id.container2_news_detail ) != null )
        {
            isTwoPane = true;

            FragmentManager fm = getSupportFragmentManager();
            listFragment = (NewsListFragment) fm.findFragmentById( R.id.fragment_news_list );
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

    /* NewsListFragment.Callbacks */

    @Override
    public void onDataLoaded()
    {
        pagerFragment.getViewPager().getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemSelected( int newsIndex )
    {
        if( isTwoPane )
        {
            pagerFragment.getViewPager().setCurrentItem( newsIndex, false );
        }
        else
        {
            Intent intent = new Intent( this, NewsDetailActivity.class );
            intent.putExtra( "newsIndex", newsIndex );
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
