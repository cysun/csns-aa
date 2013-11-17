package edu.csula.csns.ui;

import edu.csula.csns.R;
import edu.csula.csns.model.NewsData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

public class NewsDetailActivity extends FragmentActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news_detail );

        getActionBar().setDisplayHomeAsUpEnabled( true );

        viewPager = (ViewPager) findViewById( R.id.pager_news_detail );
        viewPager.setAdapter( new FragmentStatePagerAdapter(
            getSupportFragmentManager() ) {

            @Override
            public int getCount()
            {
                return NewsData.getInstance( NewsDetailActivity.this )
                    .getNewses()
                    .size();
            }

            @Override
            public Fragment getItem( int pos )
            {
                return createFragment( pos );
            }
        } );

        viewPager.setCurrentItem( getIntent().getIntExtra( "newsIndex", 0 ) );
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

    private Fragment createFragment( int newsIndex )
    {
        Bundle arguments = new Bundle();
        arguments.putInt( "newsIndex", newsIndex );
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments( arguments );
        return fragment;
    }

}
