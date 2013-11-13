package edu.csula.csns.model;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {

    private User user;

    private Context context;

    private static UserData userData;

    private UserData( Context context )
    {
        this.context = context;
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
