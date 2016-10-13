package it.syncroweb.android.bio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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

        User user = users.get(position);

        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_list, parent, false);
        }

        TextView nome = (TextView) convertView.findViewById(R.id.txtNome);
        nome.setText(user.getName());

        TextView data = (TextView) convertView.findViewById(R.id.txtDataNascita);
        data.setText(user.getBirthdate());

        ImageView img = (ImageView) convertView.findViewById(R.id.lvImage);
        img.setImageResource(Integer.parseInt(user.getPhoto()));

        return convertView;
    }

}