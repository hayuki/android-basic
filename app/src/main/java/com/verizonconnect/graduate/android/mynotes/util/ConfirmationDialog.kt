package com.verizonconnect.graduate.android.mynotes.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

class ConfirmationDialog : DialogFragment() {
    private lateinit var listener: ConfirmationDialogListener
    private lateinit var message: String
    
    companion object {
        const val TAG = "ConfirmationDialog"
        private const val MESSAGE_KEY = "message"
        
        fun newInstance(message: String) = ConfirmationDialog().apply {
            arguments = Bundle().apply {
                putString(MESSAGE_KEY, message)
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupArguments(arguments)
    }
    
    private fun setupArguments(arguments: Bundle?) {
        message = arguments?.get(MESSAGE_KEY) as String
    }
    
    interface ConfirmationDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ConfirmationDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                " must implement ConfirmationDialogListener"))
        }
    }
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                .setPositiveButton(android.R.string.ok)
                { _, _ -> listener.onDialogPositiveClick(this) }
                .setNegativeButton(android.R.string.cancel)
                { _, _ -> listener.onDialogNegativeClick(this) }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}