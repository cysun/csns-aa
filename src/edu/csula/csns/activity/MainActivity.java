package edu.csula.csns.activity;

import java.util.List;

import edu.csula.csns.CustomApplication;
import edu.csula.csns.R;
import edu.csula.csns.WebServiceClient;
import edu.csula.csns.model.Department;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

    private Spinner selectDepartment;

    private EditText inputUsername, inputPassword;

    private Button btnLogin, btnNologin;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        CustomApplication application = (CustomApplication) getApplication();

        selectDepartment = (Spinner) findViewById( R.id.selectDepartment );
        if( application.getDepartment() != null )
            selectDepartment.setVisibility( View.GONE );
        else
            (new GetDepartmentsTask()).execute();

        inputUsername = (EditText) findViewById( R.id.inputUsername );
        inputPassword = (EditText) findViewById( R.id.inputPassword );
        btnLogin = (Button) findViewById( R.id.btnLogin );
        btnNologin = (Button) findViewById( R.id.btnNologin );
        if( application.getUser() != null )
        {
            inputUsername.setVisibility( View.GONE );
            inputPassword.setVisibility( View.GONE );
            btnLogin.setVisibility( View.GONE );
            btnNologin.setText( R.string.btn_continue );
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    private class GetDepartmentsTask extends
        AsyncTask<Void, Void, List<Department>> {

        ProgressDialog progressDialog;

        @Override
        protected List<Department> doInBackground( Void... params )
        {
            WebServiceClient wsClient = WebServiceClient.getInstance();
            return wsClient.getDepartments();
        }

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog( MainActivity.this );
            progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
            progressDialog.setMessage( "Contacting CSNS Server ..." );
            progressDialog.setCancelable( false );
            progressDialog.setIndeterminate( true );
            progressDialog.show();
        }

        @Override
        protected void onPostExecute( List<Department> departments )
        {
            selectDepartment.setAdapter( new ArrayAdapter<Department>(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, departments ) );
            progressDialog.dismiss();
        }
    }

}
