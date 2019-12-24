package com.android_demo.utils

import android.app.Activity
import android.support.v7.app.AlertDialog
/**
 * DialogAlert
 * @desc This class will hold function for displaying Error dialog
 **/
class DialogAlert {

/**
 * Display Error dialog
 * @param title - Title to be display
 * @param message - Message to be display
 * @param btn_Postive - Button text on dialog
 */
    fun showErrorDialog(context: Activity, title: String, message: String, btn_Postive: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(
            btn_Postive
        ) { arg0, arg1 ->
            arg0.dismiss()
            context.finish()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}
