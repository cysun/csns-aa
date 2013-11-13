package edu.csula.csns.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

import edu.csula.csns.JsonUtils;
import edu.csula.csns.WebServiceClient;

public class NewsData {

    private String dept;

    private List<News> newses;

    private Date updatedOn;

    private Context context;

    private static NewsData newsData;

    private NewsData( Context context )
    {
        this.context = context;
        fromPreferences();
    }

    public static NewsData getInstance( Context context )
    {
        if( newsData == null ) newsData = new NewsData( context );
        return newsData;
    }

    public boolean update()
    {
        return update( false );
    }

    public boolean update( boolean forceUpdate )
    {
        Department department = DepartmentData.getInstance( context )
            .getDepartment();
        if( department == null ) return false;

        if( forceUpdate || dept == null || newses == null || isOutOfDate()
            || !dept.equals( department.getAbbreviation() ) )
        {
            dept = department.getAbbreviation();
            newses = WebServiceClient.getNewses( dept );
            updatedOn = new Date();
            toPreferences();
            return true;
        }

        return false;
    }

    public boolean isOutOfDate()
    {
        if( updatedOn == null ) return true;

        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.HOUR, -6 );
        return calendar.getTime().after( updatedOn );
    }

    private void fromPreferences()
    {
        SharedPreferences preferences = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE );

        dept = preferences.getString( "news.dept", null );
        if( dept == null ) return;

        newses = JsonUtils.readNewses( preferences.getString( "news.newses",
            null ) );
        updatedOn = new Date();
        updatedOn.setTime( preferences.getLong( "news.updatedOn", -1 ) );
    }

    private void toPreferences()
    {
        SharedPreferences.Editor editor = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE ).edit();

        editor.putString( "news.dept", dept );
        editor.putString( "news.newses", JsonUtils.writeNewses( newses ) );
        editor.putLong( "news.updatedOn", updatedOn.getTime() );
        editor.commit();
    }

    public String getDept()
    {
        return dept;
    }

    public List<News> getNewses()
    {
        return newses;
    }

    public Date getUpdatedOn()
    {
        return updatedOn;
    }

}
