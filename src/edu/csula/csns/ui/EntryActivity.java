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
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EntryActivity extends Activity {

    private Spinner selectDepartment;

    private EditText inputUsername, inputPassword;

    private Button btnLogin, btnNologin;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_entry );

        Department department = DepartmentData.getInstance( this )
            .getDepartment();
        User user = UserData.getInstance( this ).getUser();
        if( department != null && user != null )
        {
            startDefaultActivity();
            return;
        }

        selectDepartment = (Spinner) findViewById( R.id.selectDepartment );
        if( department != null )
        {
            Log.d( "MainActivity", "From prefs: " + department.getName() );
            selectDepartment.setVisibility( View.INVISIBLE );
        }
        else
            (new GetDepartmentsTask()).execute();

        inputUsername = (EditText) findViewById( R.id.inputUsername );
        inputPassword = (EditText) findViewById( R.id.inputPassword );
        btnLogin = (Button) findViewById( R.id.btnLogin );
        btnNologin = (Button) findViewById( R.id.btnNologin );

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

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    private void startDefaultActivity()
    {
        startActivity( new Intent( this, NewsListActivity.class ) );

        // Close this activity and remove it from the activity stack so
        // the user cannot get back to it by clicking the Back button.
        finish();
    }

    private class GetDepartmentsTask extends
        AsyncTask<Void, Void, List<Department>> {

        ProgressDialog progressDialog;

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
            selectDepartment.setAdapter( new ArrayAdapter<Department>(
                EntryActivity.this,
                android.R.layout.simple_spinner_dropdown_item, departments ) );
            progressDialog.dismiss();
        }
    }

    private class LoginTask extends AsyncTask<String, Void, User> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            progressDialog = WidgetUtils.getDefaultProgressDialog( EntryActivity.this );
            progressDialog.show();
        }

        @Override
        protected User doInBackground( String... params )
        {
            return WebServiceClient.login( params[0], params[1] );
        }

        @Override
        protected void onPostExecute( User user )
        {
            UserData.getInstance( EntryActivity.this ).setUser( user );
            progressDialog.dismiss();
        }
    }

    private class BtnClickListener implements View.OnClickListener {

        @Override
        public void onClick( View v )
        {
            if( selectDepartment.getVisibility() != View.INVISIBLE )
                DepartmentData.getInstance( EntryActivity.this ).setDepartment(
                    (Department) selectDepartment.getSelectedItem() );

            if( v.getId() == R.id.btnLogin )
            {
                (new LoginTask()).execute( inputUsername.getText().toString(),
                    inputPassword.getText().toString() );
            }

            startDefaultActivity();
        }
    }

}
