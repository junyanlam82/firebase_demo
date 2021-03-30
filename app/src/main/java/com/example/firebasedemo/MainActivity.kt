package com.example.firebasedemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var nameList= ArrayList<TrackItem>()
        val btnInsert :Button = findViewById(R.id.btn_Insert)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Track")
        btnInsert.setOnClickListener(){

            // Write a message to the database
            // Write a message to the database

            myRef.child("WA004").child("Name").setValue("John")
            myRef.child("WA004").child("Programme").setValue("John")
        }

        var getData = object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //var sb = StringBuilder()

                for(c in snapshot.children){
                    var name=c.child("name").getValue().toString()
                    var price=c.child("price").getValue().toString().toInt()
                    //sb.append("${name}\n")
                    nameList.add(TrackItem(name,price))
                }

                val tvMessage: TextView =findViewById(R.id.tvMessage)

                tvMessage.setText(nameList[0].name)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        myRef.addValueEventListener(getData)
        myRef.addListenerForSingleValueEvent(getData)
        val btnGet :Button=findViewById(R.id.btn_Get)
        btnGet.setOnClickListener(){


        }
    }



}