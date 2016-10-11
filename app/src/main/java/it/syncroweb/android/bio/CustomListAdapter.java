package it.syncroweb.android.bio;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/*
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
*/

class CustomListAdapter extends ArrayAdapter<User> {

    User user;
    private List<User> users;

    CustomListAdapter(Context context, int resource, List<User> objects){
         super(context, resource, objects);
         users = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_registered_date_list, parent, false);
        }
        User user = users.get(position);

        TextView nome = (TextView) convertView.findViewById(R.id.txtNome);
        nome.setText(user.getName());

        TextView data = (TextView) convertView.findViewById(R.id.txtDataNascita);
        data.setText(user.getBirthdate());

        ImageView img = (ImageView) convertView.findViewById(R.id.lvImage);
        img.setImageResource(Integer.parseInt(user.getPhoto()));

        return convertView;
    }

}