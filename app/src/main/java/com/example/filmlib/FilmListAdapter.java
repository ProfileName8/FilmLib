package com.example.filmlib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class FilmListAdapter extends ArrayAdapter<Film>{

    private Context context;
    private Film[] Films;

    public FilmListAdapter(Context context, Film[] Films){
        super(context, R.layout.item, Films);
        this.context = context;
        this.Films = Films;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item,parent,false);

        TextView itemName = (TextView) view.findViewById(R.id.Item_name);
        TextView itemStatus = (TextView) view.findViewById(R.id.Item_status);
        TextView itemRating = (TextView) view.findViewById(R.id.Item_rating);
        TextView itemDescription = (TextView) view.findViewById(R.id.Item_description);

        itemName.setText(this.Films[position].getName());
        itemStatus.setText(this.Films[position].getStatus());
        itemRating.setText(this.Films[position].getRating());
        itemDescription.setText(this.Films[position].getDescription());

        return view;
    }
}
