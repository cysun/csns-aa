package edu.csula.csns.ui;

import edu.csula.csns.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class NewsListActivity extends Activity implements
    NewsListFragment.Callbacks {

    private boolean isTwoPane;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news_list );

        if( findViewById( R.id.news_detail_container ) != null )
        {
            isTwoPane = true;
            ((NewsListFragment) getFragmentManager().findFragmentById(
                R.id.news_list )).getListView().setChoiceMode(
                ListView.CHOICE_MODE_SINGLE );
        }
    }

    @Override
    public void onItemSelected( int newsIndex )
    {
        if( isTwoPane )
        {
            Bundle arguments = new Bundle();
            arguments.putInt( "newsIndex", newsIndex );
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments( arguments );
            getFragmentManager().beginTransaction()
                .replace( R.id.news_detail_container, fragment )
                .commit();
        }
        else
        {
            Intent intent = new Intent( this, NewsDetailActivity.class );
            intent.putExtra( "newsIndex", newsIndex );
            startActivity( intent );
        }
    }

}
