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
