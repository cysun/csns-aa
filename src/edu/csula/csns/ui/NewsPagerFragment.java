package edu.csula.csns.ui;

import edu.csula.csns.R;
import edu.csula.csns.ui.adapter.NewsPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

        return view;
    }

    public ViewPager getViewPager()
    {
        return viewPager;
    }

}
