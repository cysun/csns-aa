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

public class DepartmentData {

    private Context context;

    private Department department;

    private static DepartmentData departmentData;

    private DepartmentData( Context context )
    {
        // It's important that we use application context because other
        // contexts (e.g. activities) may be destroyed by the system.
        this.context = context.getApplicationContext();
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

    public void clear()
    {
        department = null;

        SharedPreferences.Editor editor = context.getSharedPreferences( "csns",
            Context.MODE_PRIVATE ).edit();
        editor.remove( "department.id" );
        editor.remove( "department.name" );
        editor.remove( "department.abbreviation" );
        editor.commit();
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
