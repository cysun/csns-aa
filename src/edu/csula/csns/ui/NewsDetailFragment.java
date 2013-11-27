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
            content.getSettings().setDefaultFontSize(
                getResources().getInteger( R.integer.news_details_fontsize ) );
            content.loadDataWithBaseURL( "file:///android_asset/",
                news.getContentWithAttachments(), "text/html", "UTF-8", null );
        }

        return view;
    }

}
