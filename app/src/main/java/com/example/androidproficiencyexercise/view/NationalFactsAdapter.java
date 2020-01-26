package com.example.androidproficiencyexercise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidproficiencyexercise.R;
import com.example.androidproficiencyexercise.model.NationalFact;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * NationalFactAdapter is responsible for rendering items in the ListView.
 * It is optimized.
 */
public class NationalFactsAdapter extends ArrayAdapter<NationalFact> {
    private int resource;

    /**
     * This constructor is necessary to store Context and List<NationalFact>.
     *
     * @param context       from the Activity, use getContext() to retrieve.
     * @param resource      from the Activity, explicitly use a variable to store .
     * @param nationalFacts from the Activity, use getItem(position) to retrieve.
     */
    NationalFactsAdapter(@NonNull Context context, int resource, List<NationalFact> nationalFacts) {
        super(context, resource, nationalFacts);
        this.resource = resource;
    }

    /**
     * Creates and renders view for each item. Employs ViewHolder pattern for caching.
     * The convertView is used to determined of the view was already created to avoid unnecessary creation of view.
     *
     * @param position    the position of the item in the list
     * @param convertView the current view, can be null or not depending on if the view was used before
     * @param parent      the parent
     * @return the view to be rendered
     */
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

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

        // Get the data item for this position
        NationalFact record = getItem(position);

        viewHolder.title.setText(record.getTitle());
        viewHolder.description.setText(record.getDescription());
        Picasso.with(getContext()).load(record.getImageHref()).into(viewHolder.image);

        return convertView;
    }

    /**
     * ViewHolder design pattern to enable caching for performance.
     */
    private class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }

}
