package edu.csula.csns.ui.adapter;

import java.util.List;

import edu.csula.csns.model.News;
import edu.csula.csns.model.NewsData;
import edu.csula.csns.ui.NewsDetailFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    private int count;

    public NewsPagerAdapter( Context context, FragmentManager fm )
    {
        super( fm );

        this.context = context;
        updateCount();
    }

    private void updateCount()
    {
        List<News> newses = NewsData.getInstance( context ).getNewses();
        count = newses != null ? newses.size() : 0;
    }

    @Override
    public int getCount()
    {
        return count;
    }

    @Override
    public Fragment getItem( int pos )
    {
        Bundle arguments = new Bundle();
        arguments.putInt( "newsIndex", pos );
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments( arguments );
        return fragment;
    }

    @Override
    public void notifyDataSetChanged()
    {
        updateCount();
        super.notifyDataSetChanged();
    }

}
