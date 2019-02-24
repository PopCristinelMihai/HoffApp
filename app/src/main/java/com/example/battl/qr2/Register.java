package com.example.battl.qr2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Register extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText,editText2;
    private static final String DB_URL= "jdbc:mysql://mysql6002.site4now.net/db_a37d85_mydb";
    private static final String USER = "a37d85_mydb";
    private static final String PASS = "metin2ro";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button= findViewById(R.id.button2);
        editText= findViewById(R.id.editText);
        editText2= findViewById(R.id.editText2);
        textView= findViewById(R.id.textView);


    }

    public void btnConn(View view)
    {
        Send objsend = new Send();
        objsend.execute("");
    }

    private class Send extends AsyncTask<String,String,String>
    {
        String msg = "";
        String email = editText.getText().toString();
        String pass = editText2.getText().toString();


        Context context = getApplicationContext();
        CharSequence tText="Please insert all credentials";
        CharSequence tText2="Adresa de email este deja inregistrata";
        int duration = Toast.LENGTH_SHORT;
        Toast toast2=Toast.makeText(context,tText2,duration);
        Toast toast=Toast.makeText(context,tText,duration);

        @Override
        protected  void onPreExecute() {textView.setText("Please Wait Inserting");}

        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                if(conn==null){
                    msg="Connection goes wrong";
                }else{

                    if(!pass.isEmpty()& !email.isEmpty()) {

                        String query = "INSERT INTO reg (email,pass) VALUES ('" + email + "','" + pass+ "') ";
                        String query2 = "SELECT email FROM reg WHERE email = '" + email + "'";

                        int flag =0;

                        Statement stmt2 = conn.createStatement();
                        ResultSet r2 = stmt2.executeQuery(query2);



                        if(!r2.next())
                        {
                            //r2.getString("email") == null
                            Statement stmt=conn.createStatement();
                            stmt.executeUpdate(query);
                            msg = "Inserted successfuly";
                        }else toast2.show();



                    }else {

                        //toast.show();
                    }
                }
                conn.close();

            }catch (Exception e)
            {
                msg="Connection goes wrong 2";
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {textView.setText(msg);}
    }


}
