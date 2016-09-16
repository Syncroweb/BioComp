package com.syncroweb.biocomp20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Infos extends AppCompatActivity {


    private TextView txtBioritmi;

    //Read the file from the root.
    //The file contains all the infos
    //about the BioCompatibility.
    //Once read, all the text will be
    //displayed into a EditText
    @Override
    public void onCreate(Bundle savedInstanceState) {
        String text;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        txtBioritmi = (TextView) this.findViewById(R.id.txtBioritmi);
    }
/*

        text = readFile("/files/bioritmi.txt"); //mettere il percorso esatto
        txtBioritmi.setText(text);
    }

    //Function that read the file and
    //return the content
    public String readFile(String path)
    {
        String content = null;
        File file = new File(path);
        FileReader reader = null;

        try
        {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return content;
    } */
}
