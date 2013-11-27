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

import java.text.DateFormat;
import java.util.List;

import edu.csula.csns.R;
import edu.csula.csns.model.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {

    private Context context;

    private List<News> newses;

    public NewsListAdapter( Context context, List<News> newses )
    {
        this.context = context;
        this.newses = newses;
    }

    @Override
    public int getCount()
    {
        return newses != null ? newses.size() : 0;
    }

    @Override
    public Object getItem( int position )
    {
        return newses.get( position );
    }

    @Override
    public long getItemId( int position )
    {
        return newses.get( position ).getId();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        View view = convertView;
        if( view == null )
        {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( R.layout.view_news_list_item, null );
        }

        News news = newses.get( position );
        TextView title = (TextView) view.findViewById( R.id.tview_news_title );
        title.setText( news.getTitle() );

        TextView subtitle = (TextView) view.findViewById( R.id.tview_news_subtitle );
        subtitle.setText( "Posted by " + news.getAuthor() + " on "
            + DateFormat.getDateInstance().format( news.getPublishDate() ) );

        return view;
    }

}
