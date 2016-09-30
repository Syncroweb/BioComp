package it.syncroweb.android.bio;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemName;
    private final String[] itemBirth;
    private final int[] imgid;

    public CustomListAdapter(Activity context, String[] itemName, String[] itemBirth, int[] imgid) {
        super(context, R.layout.style_list, itemName);

        this.context=context;
        this.itemName = itemName;
        this.itemBirth = itemBirth;
        this.imgid = imgid;
    }

    @NonNull
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.style_list, null,true);

        TextView txtNome = (TextView) rowView.findViewById(R.id.txtNome);
        TextView txtDataNascita = (TextView) rowView.findViewById(R.id.txtDataNascita);
        ImageView lvImage = (ImageView) rowView.findViewById(R.id.lvImage);

        txtNome.setText(itemName[position]);
        lvImage.setImageResource(imgid[position]);
        txtDataNascita.setText(itemBirth[position]);
        return rowView;
    };
}
