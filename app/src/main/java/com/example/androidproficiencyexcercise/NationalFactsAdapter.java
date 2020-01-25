package com.example.androidproficiencyexcercise;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidproficiencyexcercise.model.NationalFact;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NationalFactsAdapter extends ArrayAdapter<NationalFact> {
    private Context context;
    private List<NationalFact> nationalFacts;

    NationalFactsAdapter(@NonNull Context context, int resource, List<NationalFact> nationalFacts) {
        super(context, resource, nationalFacts);
        this.context = context;
        this.nationalFacts = nationalFacts;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.row_layout, parent, false);

        NationalFact record = nationalFacts.get(position);
        TextView title = rowView.findViewById(R.id.factTitle);
        title.setText(record.getTitle());

        TextView description = rowView.findViewById(R.id.factDescription);
        description.setText(record.getDescription());

        ImageView image = rowView.findViewById(R.id.factImage);
        Picasso.with(context).load(record.getImageHref()).into(image);

        return rowView;
    }


}
