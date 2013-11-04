package edu.csula.csns.model;

import java.io.Serializable;

import android.content.SharedPreferences;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public Department()
    {
    }

    public static Department fromPreferences( SharedPreferences preferences )
    {
        if( preferences.getLong( "department.id", -1 ) < 0 ) return null;

        Department department = new Department();
        department.id = preferences.getLong( "department.id", -1 );
        department.name = preferences.getString( "department.name", "" );

        return department;
    }

    public void toPreferences( SharedPreferences preferences )
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong( "department.id", id );
        editor.putString( "department.name", name );
        editor.commit();
    }

    @Override
    public String toString()
    {
        return name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

}
