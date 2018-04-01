package com.example.utilisateur.projetl3.Aides;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utilisateur.projetl3.R;

import java.util.List;

/**
 * Created by Utilisateur on 31/03/2018.
 */

public class AidesAdapter extends ArrayAdapter<Aide> {

    public AidesAdapter(Context context, List<Aide> aides) {
        super(context, 0, aides);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_aides,parent, false);
        }

        AidesViewHolder viewHolder = (AidesViewHolder) convertView.getTag();
        if(viewHolder == null) {
            viewHolder = new AidesViewHolder();
            viewHolder.intitule = (TextView) convertView.findViewById(R.id.intitule);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        }

        Aide aide = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.intitule.setText(aide.getIntitule());
        viewHolder.description.setText(aide.getDescription());
        viewHolder.icon.setImageDrawable(new ColorDrawable(aide.getColor()));

        return convertView;
    }

    class AidesViewHolder{
        public TextView intitule;
        public TextView description;
        public ImageView icon;
    }
}
