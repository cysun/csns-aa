package edu.csula.csns.model;

import android.content.Context;
import android.content.SharedPreferences;

public class DepartmentData {

    private Department department;

    private Context context;

    private static DepartmentData departmentData;

    private DepartmentData( Context context )
    {
        this.context = context;
        fromPreferences();
    }

    public static DepartmentData getInstance( Context context )
    {
        if( departmentData == null )
            departmentData = new DepartmentData( context );

        return departmentData;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment( Department department )
    {
        this.department = department;
        if( department != null ) toPreferences();
    }

    private void fromPreferences()
    {
        SharedPreferences preferences = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE );

        if( preferences.getLong( "department.id", -1 ) < 0 )
        {
            department = null;
            return;
        }

        department = new Department();
        department.id = preferences.getLong( "department.id", -1 );
        department.name = preferences.getString( "department.name", null );
        department.abbreviation = preferences.getString(
            "department.abbreviation", null );
    }

    private void toPreferences()
    {
        SharedPreferences.Editor editor = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE ).edit();

        editor.putLong( "department.id", department.id );
        editor.putString( "department.name", department.name );
        editor.putString( "department.abbreviation", department.abbreviation );
        editor.commit();
    }

}
