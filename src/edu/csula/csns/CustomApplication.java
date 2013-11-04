package edu.csula.csns;

import edu.csula.csns.model.Department;
import edu.csula.csns.model.User;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class CustomApplication extends Application {

    private User user;

    private Department department;

    @Override
    public void onCreate()
    {
        super.onCreate();

        SharedPreferences preferences = getSharedPreferences( "csns",
            Context.MODE_PRIVATE );
        user = User.fromPreferences( preferences );
        department = Department.fromPreferences( preferences );
    }

    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment( Department department )
    {
        this.department = department;
    }

}
