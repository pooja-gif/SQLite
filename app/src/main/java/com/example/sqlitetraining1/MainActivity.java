package com.example.sqlitetraining1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnInsert, btnView, btnUpdate, btnDelete;
    EditText text_name,text_surname,text_marks, text_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);


        text_id = findViewById(R.id.ed_id);
        text_name = findViewById(R.id.ed_name);
        text_surname = findViewById(R.id.ed_surname);
        text_marks = findViewById(R.id.ed_marks);
        btnInsert = findViewById(R.id.btn_insert);
        btnView = findViewById(R.id.btn_view);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean isdatainserted =  dbHelper.insertData(text_name.getText().toString(),text_surname.getText().toString(),text_marks.getText().toString());

            if(isdatainserted == true){
                Toast.makeText(MainActivity.this,"Insertion Successful!",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this,"Insertion Failed!",Toast.LENGTH_SHORT).show();

            }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur = dbHelper.dataViewAll();

                if(cur.getCount() == 0){

                    showMessage("Error","Data Not Found");
                }

                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()){
                    buffer.append("ID: "+cur.getString(0)+ "\n");
                    buffer.append("NAME: "+cur.getString(1)+ "\n");
                    buffer.append("SURNAME: "+cur.getString(2)+ "\n");
                    buffer.append("MARKS: "+cur.getString(3)+ "\n");
                     showMessage("Data",buffer.toString());


                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = dbHelper.updateData(text_id.getText().toString(),text_name.getText().toString(),text_surname.getText().toString(),
                        text_marks.getText().toString());
                if(isUpdate == true){
                    Toast.makeText(MainActivity.this,"Data Updated Successfully!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data Updated Failed!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isdeleted = dbHelper.deleteData(text_id.getText().toString());
                if(isdeleted>0){
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
