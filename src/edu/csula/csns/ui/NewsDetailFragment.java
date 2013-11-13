package edu.csula.csns.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import edu.csula.csns.R;
import edu.csula.csns.model.News;
import edu.csula.csns.model.NewsData;

public class NewsDetailFragment extends Fragment {

    private News news;

    public NewsDetailFragment()
    {
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        if( getArguments().containsKey( "newsIndex" ) )
            news = NewsData.getInstance( getActivity() )
                .getNewses()
                .get( getArguments().getInt( "newsIndex" ) );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState )
    {
        View rootView = inflater.inflate( R.layout.fragment_news_detail,
            container, false );

        if( news != null )
        {
            WebView webView = (WebView) rootView.findViewById( R.id.news_detail );
            webView.loadData( news.getContent(), "text/html", "UTF-8" );
        }

        return rootView;
    }

}
