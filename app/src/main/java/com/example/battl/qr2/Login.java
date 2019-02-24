package com.example.battl.qr2;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public  class Login extends AppCompatActivity {
    TextView textView3;
    private Button button;
    private Button buttonRegister;
    private Button button2;


   EditText editTextPassword;
  static  EditText editTextEmail;


    private static final String DB_URL= "jdbc:mysql://mysql6002.site4now.net/db_a37d85_mydb";
    private static final String USER = "a37d85_mydb";
    private static final String PASS = "metin2ro";



    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonRegister= findViewById(R.id.buttonRegister);
        button= findViewById(R.id.button);

        editTextEmail= findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textView3= findViewById(R.id.textView3);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });


    }


    public void btnConn(View view)
    {
        Send objsend = new Send();
        objsend.execute("");
    }

    private  class Send extends AsyncTask<String,String,String>
    {

        String msg="";

        String email = editTextEmail.getText().toString();



        String pass=editTextPassword.getText().toString();

        @Override
        protected String doInBackground(String... strings) {

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                if(conn==null)
                {
                    msg="You picked the wrong house foof";
                }else {
                    if (!pass.isEmpty() && !email.isEmpty()) {
                        String query = "SELECT * FROM reg";
                        String query2="SELECT credite FROM reg WHERE email ='" + email + "'";
                        Statement stmt = conn.createStatement();
                        ResultSet r1 = stmt.executeQuery(query);



                        Statement stmt2 = conn.createStatement();
                        ResultSet r2 =stmt2 .executeQuery(query2);
                        int i=0;
                        int k=0;
                        Intent intentCredite = new Intent(Login.this,Profil.class);
                        Bundle extras = new Bundle();

                        while (r1.next()) {
                            i++;

                            if (email.equals(r1.getString("email"))) {
                                if (pass.equals(r1.getString("pass"))) {
                                    msg="Logged";
                                    r2.next();
                                    extras.putString("email",editTextEmail.getText().toString());
                                    extras.putString("credits",r2.getString("credite"));
                                    intentCredite.putExtras(extras);
                                    startActivity(intentCredite);



                                    break;
                                    } else
                                {k++;}


                            } else {k++;}

                        }

                    }

                }

                textView3.setText(msg);

            }
            catch (Exception e) {
                e.printStackTrace();

            }

            return msg;
        }
    }


    public void openMainActivity() {
        Intent intent=new Intent(Login.this,QRScan.class);
        startActivity(intent);

    }

    public void openRegister()
    {
        Intent intentRegister=new Intent(Login.this,Register.class);
        startActivity(intentRegister);
    }

}



