package edu.csula.csns;

import android.app.ProgressDialog;
import android.content.Context;

public class WidgetUtils {

    public static ProgressDialog getDefaultProgressDialog( Context context )
    {
        ProgressDialog progressDialog = new ProgressDialog( context );

        progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
        progressDialog.setMessage( "Contacting CSNS Server ..." );
        progressDialog.setCancelable( false );
        progressDialog.setIndeterminate( true );

        return progressDialog;
    }

}
