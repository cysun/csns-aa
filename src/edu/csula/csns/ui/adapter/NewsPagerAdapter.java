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
