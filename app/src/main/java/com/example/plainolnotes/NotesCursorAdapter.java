package com.example.plainolnotes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesCursorAdapter extends CursorAdapter{
    public NotesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(
                R.layout.note_list_item, parent, false
        );
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String noteText = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.NOTE_TEXT));

        String noteTime = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.NOTE_CREATED));

        String noteTitle = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.NOTE_TITLE));

        String outputTime = Character.toString(noteTime.charAt(0))
                            + Character.toString(noteTime.charAt(1))
                            + Character.toString(noteTime.charAt(2))
                            + Character.toString(noteTime.charAt(3))
                            + Character.toString(noteTime.charAt(4))
                            + Character.toString(noteTime.charAt(5))
                            + Character.toString(noteTime.charAt(6))
                            + Character.toString(noteTime.charAt(7))
                            + Character.toString(noteTime.charAt(8))
                            + Character.toString(noteTime.charAt(9));
//        for(int i; i<9; i++){
//            outputTime = outputTime + Character.toString(noteTime.charAt(i));
//        }




        int pos = noteText.indexOf(10);
        if (pos != -1) {
            noteText = noteText.substring(0, pos) + " ...";
        }

        TextView tv = (TextView) view.findViewById(R.id.tvNote);
        tv.setText(noteText);

        TextView timeview = (TextView) view.findViewById(R.id.timeView);
        timeview.setText(outputTime);

        TextView titleview = (TextView) view.findViewById(R.id.tvNoteTitle);
        if (noteTitle.length()==0) {
            titleview.setText("UNTITLE");
        }else {
        titleview.setText(noteTitle);
        }

    }
}
