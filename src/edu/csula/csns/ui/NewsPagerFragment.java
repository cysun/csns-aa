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
import edu.csula.csns.ui.adapter.NewsPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsPagerFragment extends Fragment {

    private ViewPager viewPager;

    public NewsPagerFragment()
    {
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.fragment_news_pager, container,
            false );

        viewPager = (ViewPager) view.findViewById( R.id.pager_news_detail );
        viewPager.setAdapter( new NewsPagerAdapter( getActivity(),
            getChildFragmentManager() ) );

        int newsIndex = 0;
        if( getArguments() != null )
            newsIndex = getArguments().getInt( "newsIndex", 0 );
        viewPager.setCurrentItem( newsIndex );

        if( getActivity() instanceof OnPageChangeListener )
        {
            OnPageChangeListener onPageChangeListener = (OnPageChangeListener) getActivity();
            viewPager.setOnPageChangeListener( onPageChangeListener );
            onPageChangeListener.onPageSelected( newsIndex );
        }

        return view;
    }

    public ViewPager getViewPager()
    {
        return viewPager;
    }

}
