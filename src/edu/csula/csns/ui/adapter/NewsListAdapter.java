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
