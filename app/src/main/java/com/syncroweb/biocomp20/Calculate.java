package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calculate extends AppCompatActivity {

    final int CYCLES = 3;
    final int LOAD_OR_NEW_ID = 1161;
    final int RESULT_OK_LIST = 1618;
    final int RESULT_OK_DATE = 1588;

    private String labelTags;


    private TextView lblDateOne;
    private TextView lblDateTwo;
    private TextView lblNameOne;
    private TextView lblNameTwo;

    private TextView[] vetCalculatedLables = new TextView[CYCLES];

    private Button btnCalculate;

    // Function that first starts when the activity
    // is launched. It actually starts the components
    // allowing the following operations to work
    // properly
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        lblDateOne = (TextView)this.findViewById(R.id.lblDateOne);
        lblDateTwo = (TextView)this.findViewById(R.id.lblDateTwo);
        lblNameOne = (TextView)this.findViewById(R.id.lblNameOne);
        lblNameTwo = (TextView)this.findViewById(R.id.lblNameTwo);

        vetCalculatedLables[0] = (TextView)this.findViewById(R.id.lblCalculatedEm);
        vetCalculatedLables[1] = (TextView)this.findViewById(R.id.lblCalculatedInt);
        vetCalculatedLables[2] = (TextView)this.findViewById(R.id.lblCalculatedPh);

        btnCalculate = (Button)this.findViewById(R.id.btnCalculate);
        assert btnCalculate != null;
        btnCalculate.setClickable(false);

        // Function that call the real
        // BioCompatibility function and
        // switch between the button
        // "Calculate" and "Reset"
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String buttonTag = btnCalculate.getTag().toString();

                if(buttonTag.equals("calculate"))
                {
                    calculateBioCompatibility();
                    btnCalculate.setText("Reset");
                    btnCalculate.setTag("reset");
                }
                else
                    resetGui();
            }
        });


        // Function that start the activity
        // "calculate_load_or_new" and wait for
        // the user to put the dates
        lblDateOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                labelTags = view.getTag().toString();
                Intent i = new Intent(view.getContext(), CalculateLoadOrNew.class);
                startActivityForResult(i, LOAD_OR_NEW_ID);
            }
        });

        // Function that start the activity
        // "calculate_load_or_new" and wait for
        // the user to put the dates
        lblDateTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                labelTags = view.getTag().toString();
                Intent i = new Intent(view.getContext(), CalculateLoadOrNew.class);
                startActivityForResult(i, LOAD_OR_NEW_ID);
            }
        });
    }

    // Function that allows the program
    // to get and display the Dates chosen
    // by the users with the "calculate_load_or_new"
    // activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == LOAD_OR_NEW_ID)
        {
            try
            {
                switch (resultCode)
                {
                    case RESULT_OK_DATE:
                        if (labelTags.equals("dateOne"))
                        {
                            lblDateOne.setText(data.getStringExtra("date"));
                            lblDateOne.setClickable(false);
                        }
                        else
                        {
                            lblDateTwo.setText(data.getStringExtra("data"));
                            lblDateTwo.setClickable(false);
                            btnCalculate.setClickable(true);
                        }
                        break;

                    case RESULT_OK_LIST:
                        String name;
                        if (labelTags.equals("dateOne"))
                        {
                            name = data.getStringExtra("name") + " " + data.getStringExtra("surname");
                            lblNameOne.setText(name);
                            lblDateOne.setText(data.getStringExtra("date"));
                            lblDateOne.setClickable(false);
                        }
                        else
                        {
                            lblDateTwo.setText(data.getStringExtra("data"));
                            name = data.getStringExtra("name") + " " + data.getStringExtra("surname");
                            lblNameTwo.setText(name);
                            lblDateTwo.setText(data.getStringExtra("date"));
                            lblDateTwo.setClickable(false);
                            btnCalculate.setClickable(true);
                        }
                        break;
                }
            }
            catch (Error error)
            {
                Toast.makeText( getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



    // Function that calculate the BioCompatibility
    // between the two selected dates
    // It uses several other sub-functions to work
    public void calculateBioCompatibility()
    {
        Date[] vetDates = new Date[2];

        final int[] vetCostants = {28, 33, 23};
        int[] vetResults = new int[CYCLES];

        int difference;


        try {   vetDates[0] = DateFormatConverter(lblDateOne.getText().toString());  }
        catch(Error error) {    Toast.makeText(         getApplicationContext(),
                error.getMessage(),
                Toast.LENGTH_SHORT).show();  }

        try {   vetDates[1] = (Date)lblDateTwo.getText();                            }
        catch(Error error) {    Toast.makeText(         getApplicationContext(),
                error.getMessage(),
                Toast.LENGTH_SHORT).show();  }


        difference = actualDifference(vetDates[0], vetDates[1]);

        for(int i = 0; i < CYCLES; i++)
            vetResults[i] = difference - ( (int)( difference / vetCostants[i] ) * vetCostants[i] );

        for(int i = 0; i < CYCLES; i++)
            vetCalculatedLables[i].setText(vetResults[i]);

        writeComments(vetResults);
    }

    // Function that converts the date String
    // into a Date and then returns it
    private Date DateFormatConverter(String myDateString)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date myNewDate;

        try {   myNewDate = dateFormat.parse(myDateString);         }
        catch (java.text.ParseException e) {    e.printStackTrace();
            return null;        }
        return myNewDate;
    }

    // Function that calculate the difference
    // between the two selected dates
    public static int actualDifference(Date date1, Date date2)
    {
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();
        gc1.setTime(date1);
        gc2.setTime(date2);

        long millis = gc2.getTimeInMillis() - gc1.getTimeInMillis();
        return  Math.abs((int) (millis / 1000 / 24 / 60 / 60));
    }

    // Function that displays with Toasts
    // the results of the calculations
    public void writeComments(int[] vetResults)
    {
        String[] Messages = new String[CYCLES];

        double[] vetPhysical =     {    99.9, 92.3, 81.6, 73.9, 65.2, 56.5, 47.8, 39.1, 30.4, 21.7,
                13, 4.3, 4.3, 13, 21.7, 30.4, 39.1, 47.8, 56.5, 65.2, 73.9,
                81.6, 92.3, 99.9                                            };

        double[] vetEmotional =    {    99.9, 93, 86, 79, 71, 64, 57, 50, 43, 36, 29, 21, 14, 7, 0,
                7, 14, 21, 29, 36, 43, 50, 57, 64, 71, 79, 86, 93, 99.9     };

        double[] vetIntellectual = {    99.9, 94, 88, 82, 76, 70, 64, 58, 52, 46, 39, 33, 27, 21,
                15, 9, 3, 3, 9, 15, 21, 27, 33, 39, 46, 52, 58, 64, 70,
                76, 82, 88, 94, 99.9                                        };

        // Emotional
        Messages[0] = Double.toString(vetEmotional[vetResults[0]]) + "%";

        if (vetEmotional[vetResults[0]] < 30)
            Messages[0] += ", " + this.getString(R.string.emotivo_1);
        if (vetEmotional[vetResults[0]] > 30)
            Messages[0] += ", " + this.getString(R.string.emotivo_2);
        if (vetEmotional[vetResults[0]] > 60)
            Messages[0] += ", " + this.getString(R.string.emotivo_3);
        if (vetEmotional[vetResults[0]] > 80)
            Messages[0] += ", " + this.getString(R.string.emotivo_4);

        // Intellectual
        Messages[1] = Double.toString(vetIntellectual[vetResults[1]]) + "%";

        if (vetIntellectual[vetResults[1]] < 30)
            Messages[1] = ", " +  this.getString(R.string.intellettuale_1);
        if (vetIntellectual[vetResults[1]] > 30)
            Messages[1] = ", " + this.getString(R.string.intellettuale_2);
        if (vetIntellectual[vetResults[1]] > 60)
            Messages[1] = ", " + this.getString(R.string.intellettuale_3);
        if (vetIntellectual[vetResults[1]] > 80)
            Messages[1] = ", " + this.getString(R.string.intellettuale_4);

        // Physical
        Messages[2] = Double.toString(vetPhysical[vetResults[2]]) + "%";

        if (vetPhysical[vetResults[2]] < 30)
            Messages[2] = ", " + this.getString(R.string.fisico_1);
        if (vetPhysical[vetResults[2]] > 30)
            Messages[2] = ", " + this.getString(R.string.fisico_2);
        if (vetPhysical[vetResults[2]] > 60)
            Messages[2] = ", " + this.getString(R.string.fisico_3);
        if (vetPhysical[vetResults[2]] > 80)
            Messages[2] = ", " + this.getString(R.string.fisico_4);

        //Results Toasts
        for(int i = 0; i < CYCLES; i++)
            Toast.makeText(getApplicationContext(), Messages[i], Toast.LENGTH_SHORT).show();
    }

    // Function that resets the GUI in order to
    // make it ready to be used again
    public void resetGui()
    {
        for(int i = 0; i < CYCLES; i++)
            vetCalculatedLables[i].setText("");

        lblDateOne.setText("Choose a Date!");
        lblDateOne.setClickable(true);

        lblDateTwo.setText("Choose a Date!");
        lblDateTwo.setClickable(true);

        btnCalculate.setText("Calculate");
        btnCalculate.setTag("calculate");
        btnCalculate.setClickable(false);
    }
}
