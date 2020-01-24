package com.example.androidproficiencyexcercise;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidproficiencyexcercise.model.CanadaRecord;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<CanadaRecord> {
    private final String TAG = "RecordAdapter";
    private Context context = null;
    private List<CanadaRecord> recordsOfCanada = null;

    public RecordAdapter(@NonNull Context context, int resource, List<CanadaRecord> recordsOfCanada) {
        super(context, resource, recordsOfCanada);
        this.context = context;
        this.recordsOfCanada = recordsOfCanada;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.row_layout, parent, false);

        CanadaRecord record = recordsOfCanada.get(position);
        TextView title = rowView.findViewById(R.id.recordTitle);
        title.setText(record.getTitle());

        TextView description = rowView.findViewById(R.id.recordDescription);
        description.setText(record.getDescription());
        Log.d(TAG, "getView: desc" + record.getDescription());

        ImageView image = rowView.findViewById(R.id.recordImage);
        image.setImageURI(record.getImageReference() == null ? null : Uri.parse(record.getImageReference()));

        return rowView;
    }
}
