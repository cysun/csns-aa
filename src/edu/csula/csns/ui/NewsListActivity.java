package edu.csula.csns.ui;

import edu.csula.csns.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class NewsListActivity extends FragmentActivity implements
    NewsListFragment.Callbacks {

    private boolean isTwoPane;

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
    public void onDataLoaded()
    {
        pagerFragment.getViewPager().getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemSelected( int newsIndex )
    {
        if( isTwoPane )
            pagerFragment.getViewPager().setCurrentItem( newsIndex );
        else
        {
            Intent intent = new Intent( this, NewsDetailActivity.class );
            intent.putExtra( "newsIndex", newsIndex );
            startActivity( intent );
        }
    }

}
