package edu.csula.csns.model;

import java.io.Serializable;

import android.content.SharedPreferences;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessKey;

    private String firstName, lastName;

    private String username;

    private User()
    {
    }

    public static User fromPreferences( SharedPreferences preferences )
    {
        if( !preferences.contains( "user.access.key" ) ) return null;

        User user = new User();
        user.accessKey = preferences.getString( "user.access.key", "" );
        user.firstName = preferences.getString( "user.first.name", "User" );
        user.lastName = preferences.getString( "user.last.name", "CSNS" );
        user.username = preferences.getString( "user.username", "" );

        return user;
    }

    public void toPreferences( SharedPreferences preferences )
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString( "user.access.key", accessKey );
        editor.putString( "user.first.name", firstName );
        editor.putString( "user.last.name", lastName );
        editor.putString( "user.username", username );
        editor.commit();
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public void setAccessKey( String accessKey )
    {
        this.accessKey = accessKey;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

}
