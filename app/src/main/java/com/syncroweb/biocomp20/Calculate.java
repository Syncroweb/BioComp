package com.syncroweb.biocomp20;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Calculate extends AppCompatActivity {

    final int CYCLES = 3;
    final int LOAD_OR_NEW_ID = 1161;
    final int RESULT_OK_LIST = 1618;
    final int RESULT_OK_DATE = 1588;

    private String labelTags;

    private double[] vetPhysical =     {    99.9, 92.3, 81.6, 73.9, 65.2, 56.5, 47.8, 39.1, 30.4, 21.7,
            13, 4.3, 4.3, 13, 21.7, 30.4, 39.1, 47.8, 56.5, 65.2, 73.9,
            81.6, 92.3, 99.9                                            };

    private double[] vetEmotional =    {    99.9, 93, 86, 79, 71, 64, 57, 50, 43, 36, 29, 21, 14, 7, 0,
            7, 14, 21, 29, 36, 43, 50, 57, 64, 71, 79, 86, 93, 99.9     };

    private double[] vetIntellectual = {    99.9, 94, 88, 82, 76, 70, 64, 58, 52, 46, 39, 33, 27, 21,
            15, 9, 3, 3, 9, 15, 21, 27, 33, 39, 46, 52, 58, 64, 70,
            76, 82, 88, 94, 99.9                                        };


    private Button DateOne;
    private Button DateTwo;
    private TextView lblNameOne;
    private TextView lblNameTwo;
    private TextView lblDateOne;
    private TextView lblDateTwo;

    private TextView[] vetCalculatedLables = new TextView[CYCLES];

    private Button btnCalculate;

    private TextView result;

    //private Date insertOne;
    //private Date insertTwo;

    // Function that first starts when the activity
    // is launched. It actually starts the components
    // allowing the following operations to work
    // properly
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        DateOne = (Button) this.findViewById(R.id.btnDateOne);
        DateTwo = (Button) this.findViewById(R.id.btnDateTwo);
        lblNameOne = (TextView)this.findViewById(R.id.lblNameOne);
        lblNameTwo = (TextView)this.findViewById(R.id.lblNameTwo);
        lblDateOne = (TextView) findViewById(R.id.lblDateOne);
        lblDateTwo = (TextView) findViewById(R.id.lblDateTwo);

        vetCalculatedLables[0] = (TextView)this.findViewById(R.id.lblCalculatedEm);
        vetCalculatedLables[1] = (TextView)this.findViewById(R.id.lblCalculatedInt);
        vetCalculatedLables[2] = (TextView)this.findViewById(R.id.lblCalculatedPh);

        btnCalculate = (Button)this.findViewById(R.id.btnCalculate);

        result = (TextView) findViewById(R.id.txtResult);

        //Disable Calculate Button
        assert btnCalculate != null;
        btnCalculate.setEnabled(false);
        btnCalculate.setClickable(false);
        btnCalculate.setBackgroundColor(Color.argb(255, 224, 224, 224));


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
                    btnCalculate.setTag("reset");
                    btnCalculate.setText("Reset");
                }
                else
                    resetGui();
            }
        });


        // Function that start the activity
        // "calculate_load_or_new" and wait for
        // the user to put the dates
        DateOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                labelTags = view.getTag().toString();
                Intent i = new Intent(view.getContext(), CalculateLoadOrNew.class);
                startActivityForResult(i, LOAD_OR_NEW_ID);
            }
        });

        // Function that start the activity
        // "calculate_load_or_new" and wait for
        // the user to put the dates
        DateTwo.setOnClickListener(new View.OnClickListener() {
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
                            DateOne.setClickable(false);
                        }
                        else if (labelTags.equals("dateTwo"))
                        {
                            lblDateTwo.setText(data.getStringExtra("date"));
                            DateTwo.setClickable(false);
                        }
                        break;

                    case RESULT_OK_LIST:
                        String name;
                        if (labelTags.equals("dateOne"))
                        {
                            name = data.getStringExtra("name");
                            lblNameOne.setText(name);
                            lblDateOne.setText(data.getStringExtra("date"));
                            DateOne.setClickable(false);
                        }
                        else
                        {
                            lblDateTwo.setText(data.getStringExtra("data"));
                            name = data.getStringExtra("name");
                            lblNameTwo.setText(name);
                            lblDateTwo.setText(data.getStringExtra("date"));
                            DateTwo.setClickable(false);
                        }
                        break;
                }

                //Able Calculate Button
                if(!lblDateOne.getText().equals("") && !lblDateTwo.getText().equals("")){
                    btnCalculate.setEnabled(true);
                    btnCalculate.setClickable(true);
                    btnCalculate.setBackgroundColor(Color.argb(255, 255, 196, 0));
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

        try {   vetDates[0] = DateFormatConverter((String) lblDateOne.getText());   }
        catch(Error error) {    Toast.makeText(         getApplicationContext(),
                error.getMessage(),
                Toast.LENGTH_SHORT).show();  }

        try {   vetDates[1] = DateFormatConverter((String) lblDateTwo.getText());                            }
        catch(Error error) {    Toast.makeText(         getApplicationContext(),
                error.getMessage(),
                Toast.LENGTH_SHORT).show();  }


        difference = actualDifference(vetDates[0], vetDates[1]);

        for(int i = 0; i < CYCLES; i++)
            vetResults[i] = difference - ( ( difference / vetCostants[i] ) * vetCostants[i] );

        double fisico = vetPhysical[vetResults[0]];
        double emotivo = vetEmotional[vetResults[1]];
        double intellettuale = vetIntellectual[vetResults[2]];

        vetCalculatedLables[0].setText(Double.toString(fisico) + "%");   //METTERE PRECISIONE DECIMALE!!
        vetCalculatedLables[1].setText(Double.toString(emotivo) + "%");   //METTERE PRECISIONE DECIMALE!!
        vetCalculatedLables[2].setText(Double.toString(intellettuale) + "%");   //METTERE PRECISIONE DECIMALE!!


        writeComments(vetResults);
    }

    // Function that converts the date String
    // into a Date and then returns it
    private Date DateFormatConverter(String myDateString)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //setto la data per italia
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

    // Function that displays with TextView
    // the results of the calculations
    public void writeComments(int[] vetResults)
    {
        String resultMessage;
        String[] messages = new String[CYCLES];


        // Emotional
        messages[0] = "Your result is: ";

        if (vetEmotional[vetResults[0]] < 30)
            messages[0] += this.getString(R.string.emotivo_1) + " ";
        if (vetEmotional[vetResults[0]] > 30 && vetEmotional[vetResults[0]] < 60)
            messages[0] += this.getString(R.string.emotivo_2) + " ";
        if (vetEmotional[vetResults[0]] > 60 && vetEmotional[vetResults[0]] < 80)
            messages[0] += this.getString(R.string.emotivo_3) + " ";
        if (vetEmotional[vetResults[0]] > 80)
            messages[0] += this.getString(R.string.emotivo_4) + " ";

        // Intellectual
        messages[1] = "";

        if (vetIntellectual[vetResults[1]] < 30)
            messages[1] = this.getString(R.string.intellettuale_1) + " ";
        if (vetIntellectual[vetResults[1]] > 30 && vetIntellectual[vetResults[0]] < 60)
            messages[1] = this.getString(R.string.intellettuale_2) + " ";
        if (vetIntellectual[vetResults[1]] > 60 && vetIntellectual[vetResults[0]] < 80)
            messages[1] = this.getString(R.string.intellettuale_3) + " ";
        if (vetIntellectual[vetResults[1]] > 80)
            messages[1] = this.getString(R.string.intellettuale_4) + " ";

        // Physical
        messages[2] = "";

        if (vetPhysical[vetResults[2]] < 30)
            messages[2] = this.getString(R.string.fisico_1);
        if (vetPhysical[vetResults[2]] > 30 && vetPhysical[vetResults[0]] < 60)
            messages[2] = this.getString(R.string.fisico_2);
        if (vetPhysical[vetResults[2]] > 60 && vetPhysical[vetResults[0]] < 80)
            messages[2] = this.getString(R.string.fisico_3);
        if (vetPhysical[vetResults[2]] > 80)
            messages[2] = this.getString(R.string.fisico_4);

        resultMessage = messages[0] + messages[1] + messages[2];

        //Results
        result.setText(resultMessage);

            //Toast.makeText(getApplicationContext(), messages[i], Toast.LENGTH_SHORT).show();
    }

    // Function that resets the GUI in order to
    // make it ready to be used again
    public void resetGui()
    {
        for(int i = 0; i < CYCLES; i++)
            vetCalculatedLables[i].setText("");

        lblDateOne.setText("dd/mm/yyyy");
        DateOne.setClickable(true);

        lblDateTwo.setText("dd/mm/yyyy");
        DateTwo.setClickable(true);

        result.setText("");

        //Disable Calculate Button
        btnCalculate.setText("Calculate");
        btnCalculate.setTag("calculate");
        btnCalculate.setEnabled(false);
        btnCalculate.setClickable(false);
        btnCalculate.setBackgroundColor(Color.argb(255, 224, 224, 224));
    }
}
