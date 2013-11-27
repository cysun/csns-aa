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
