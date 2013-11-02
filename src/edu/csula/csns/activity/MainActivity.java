package edu.csula.csns.activity;

import edu.csula.csns.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {

    private Spinner selectDepartment;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        String departments[] = { "Computer Science", "Technology" };
        selectDepartment = (Spinner) findViewById( R.id.selectDepartment );
        selectDepartment.setAdapter( new ArrayAdapter<String>( this,
            android.R.layout.simple_spinner_dropdown_item, departments ) );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

}
