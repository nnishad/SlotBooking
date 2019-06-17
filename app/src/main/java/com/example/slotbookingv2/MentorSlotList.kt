package com.example.slotbookingv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class MentorSlotList : AppCompatActivity() {
    lateinit var parts: MutableList<String>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_slot_list)

        listView = findViewById<ListView>(R.id.mentorlistview)


        val bundle: Bundle? = intent.extras
        var list: String? = bundle?.getString("slotList")
        //textView.text=list.toString()
        val myString = list.toString()
        parts = myString.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toMutableList()

        parts.forEach {
            for (x in parts) {
                Log.d("TAG1", x)
            }
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, parts)
        listView.adapter = adapter
    }
}