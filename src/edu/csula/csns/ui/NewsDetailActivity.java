package edu.csula.csns.ui;

import edu.csula.csns.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class NewsDetailActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news_detail );

        getActionBar().setDisplayHomeAsUpEnabled( true );

        if( savedInstanceState == null )
        {
            Bundle arguments = new Bundle();
            arguments.putInt( "newsIndex",
                getIntent().getIntExtra( "newsIndex", 0 ) );
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments( arguments );
            getFragmentManager().beginTransaction()
                .add( R.id.news_detail_container, fragment )
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
