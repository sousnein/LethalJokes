package com.sousnein.lethaljokes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.jokes_item.view.*
import org.json.JSONObject

class JokeAdapter(val context: Context) : RecyclerView.Adapter<JokeAdapter.MyViewHolder>() {

    private var jokesList= mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.jokes_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textJoke.text = jokesList[position]
    }

    fun setJokeListItems(jsonResponse: String) {
        val root = JSONObject(jsonResponse)
        val valuesArray = root.getJSONArray("value")
        var index = 0
        while (index < valuesArray.length()) {
            val valueObject = valuesArray.getJSONObject(index)
            val joke = valueObject.getString("joke")

            this.jokesList.add(joke)
            index++
        }

        notifyDataSetChanged()
    }

    fun cleanJokes() {
        this.jokesList.clear()
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textJoke: AppCompatTextView = itemView.text_joke
    }
}