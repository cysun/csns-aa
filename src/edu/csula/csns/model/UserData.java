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

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {

    private User user;

    private Context context;

    private static UserData userData;

    private UserData( Context context )
    {
        this.context = context.getApplicationContext();
        fromPreferences();
    }

    public static UserData getInstance( Context context )
    {
        if( userData == null ) userData = new UserData( context );
        return userData;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
        if( user != null ) toPreferences();
    }

    public void clear()
    {
        user = null;

        SharedPreferences.Editor editor = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE ).edit();
        editor.remove( "user.access.key" );
        editor.remove( "user.first.name" );
        editor.remove( "user.last.name" );
        editor.remove( "user.username" );
        editor.commit();
    }

    private void fromPreferences()
    {
        SharedPreferences preferences = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE );

        if( !preferences.contains( "user.access.key" ) )
        {
            user = null;
            return;
        }

        user = new User();
        user.accessKey = preferences.getString( "user.access.key", null );
        user.firstName = preferences.getString( "user.first.name", null );
        user.lastName = preferences.getString( "user.last.name", null );
        user.username = preferences.getString( "user.username", null );
    }

    private void toPreferences()
    {
        SharedPreferences.Editor editor = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE ).edit();

        editor.putString( "user.access.key", user.accessKey );
        editor.putString( "user.first.name", user.firstName );
        editor.putString( "user.last.name", user.lastName );
        editor.putString( "user.username", user.username );
        editor.commit();
    }

}
