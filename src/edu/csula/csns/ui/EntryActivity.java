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
package edu.csula.csns.ui;

import java.util.List;

import edu.csula.csns.R;
import edu.csula.csns.WidgetUtils;
import edu.csula.csns.WebServiceClient;
import edu.csula.csns.model.Department;
import edu.csula.csns.model.DepartmentData;
import edu.csula.csns.model.User;
import edu.csula.csns.model.UserData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EntryActivity extends Activity {

    private Spinner spinnerDepartment;

    private EditText inputUsername, inputPassword;

    private Button btnLogin, btnNologin;

    private ProgressDialog progressDialog;

    private GetDepartmentsTask getDepartmentsTask;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_entry );

        Department department = DepartmentData.getInstance(
            getApplicationContext() ).getDepartment();
        User user = UserData.getInstance( getApplicationContext() ).getUser();
        if( department != null && user != null )
        {
            startDefaultActivity();
            return;
        }

        spinnerDepartment = (Spinner) findViewById( R.id.spinner_department );
        if( department != null )
        {
            Log.d( "MainActivity", "From prefs: " + department.getName() );
            spinnerDepartment.setVisibility( View.INVISIBLE );
        }
        else
        {
            getDepartmentsTask = new GetDepartmentsTask();
            getDepartmentsTask.execute();
        }

        inputUsername = (EditText) findViewById( R.id.input_username );
        inputPassword = (EditText) findViewById( R.id.input_password );
        btnLogin = (Button) findViewById( R.id.btn_login );
        btnNologin = (Button) findViewById( R.id.btn_nologin );

        View.OnClickListener btnClickListener = new BtnClickListener();
        btnLogin.setOnClickListener( btnClickListener );
        btnNologin.setOnClickListener( btnClickListener );

        if( user != null )
        {
            inputUsername.setVisibility( View.GONE );
            inputPassword.setVisibility( View.GONE );
            btnLogin.setVisibility( View.INVISIBLE );
            btnNologin.setText( R.string.btn_continue );
        }
    }

    /**
     * Terminates the GetDepartmentsTask if the activity is destroyed (e.g. due
     * to device rotation). The LoginTask is OK as it doesn't rely on the
     * activity.
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if( progressDialog != null ) progressDialog.dismiss();
        if( getDepartmentsTask != null ) getDepartmentsTask.cancel( true );
    }

    private void startDefaultActivity()
    {
        startActivity( new Intent( getApplicationContext(),
            NewsListActivity.class ) );

        // Close this activity and remove it from the activity stack so
        // the user cannot get back to it by clicking the Back button.
        finish();
    }

    private class GetDepartmentsTask extends
        AsyncTask<Void, Void, List<Department>> {

        @Override
        protected void onPreExecute()
        {
            progressDialog = WidgetUtils.getDefaultProgressDialog( EntryActivity.this );
            progressDialog.show();
        }

        @Override
        protected List<Department> doInBackground( Void... params )
        {
            return WebServiceClient.getDepartments();
        }

        @Override
        protected void onPostExecute( List<Department> departments )
        {
            spinnerDepartment.setAdapter( new ArrayAdapter<Department>(
                EntryActivity.this,
                android.R.layout.simple_spinner_dropdown_item, departments ) );
            progressDialog.dismiss();
        }
    }

    private class LoginTask extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground( String... params )
        {
            return WebServiceClient.login( params[0], params[1] );
        }

        @Override
        protected void onPostExecute( User user )
        {
            String msg = getResources().getString( R.string.toast_login_failure );
            if( user != null )
            {
                UserData.getInstance( getApplicationContext() ).setUser( user );
                msg = getResources().getString( R.string.toast_login_success );
            }

            Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_SHORT )
                .show();
        }
    }

    private class BtnClickListener implements View.OnClickListener {

        @Override
        public void onClick( View v )
        {
            if( spinnerDepartment.getVisibility() != View.INVISIBLE )
                DepartmentData.getInstance( getApplicationContext() )
                    .setDepartment(
                        (Department) spinnerDepartment.getSelectedItem() );

            if( v.getId() == R.id.btn_login )
            {
                (new LoginTask()).execute( inputUsername.getText().toString(),
                    inputPassword.getText().toString() );
            }

            startDefaultActivity();
        }
    }

}
