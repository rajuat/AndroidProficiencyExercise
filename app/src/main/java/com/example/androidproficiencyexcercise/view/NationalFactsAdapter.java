package com.example.androidproficiencyexcercise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidproficiencyexcercise.R;
import com.example.androidproficiencyexcercise.model.NationalFact;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NationalFactsAdapter extends ArrayAdapter<NationalFact> {
    private int resource;

    NationalFactsAdapter(@NonNull Context context, int resource, List<NationalFact> nationalFacts) {
        super(context, resource, nationalFacts);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        NationalFact record = getItem(position);

        // Lookup cache stored in tag using ViewHolder pattern
        ViewHolder viewHolder;
        // Use convertView if it was created before
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resource, parent, false);

            viewHolder.title = convertView.findViewById(R.id.factTitle);
            viewHolder.description = convertView.findViewById(R.id.factDescription);
            viewHolder.image = convertView.findViewById(R.id.factImage);
            //cache the view for later lookup
            convertView.setTag(viewHolder);
        } else {
            // Get from the cache
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(record.getTitle());
        viewHolder.description.setText(record.getDescription());
        Picasso.with(getContext()).load(record.getImageHref()).into(viewHolder.image);

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }

}
