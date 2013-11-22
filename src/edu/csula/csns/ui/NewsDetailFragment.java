package edu.csula.csns.ui;

import java.text.DateFormat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

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
        View view = inflater.inflate( R.layout.fragment_news_detail, container,
            false );

        if( news != null )
        {
            TextView title = (TextView) view.findViewById( R.id.tview_news_title );
            title.setText( news.getTitle() );

            TextView subtitle = (TextView) view.findViewById( R.id.tview_news_subtitle );
            subtitle.setText( "Posted by " + news.getAuthor() + " on "
                + DateFormat.getDateInstance().format( news.getPublishDate() ) );

            WebView content = (WebView) view.findViewById( R.id.wview_news_content );
            content.loadDataWithBaseURL( "file:///android_asset/",
                news.getContentWithAttachments(), "text/html", "UTF-8", null );
        }

        return view;
    }

}
