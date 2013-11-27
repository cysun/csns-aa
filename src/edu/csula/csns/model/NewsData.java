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
        update();
    }

    public static synchronized NewsData getInstance( Context context )
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
