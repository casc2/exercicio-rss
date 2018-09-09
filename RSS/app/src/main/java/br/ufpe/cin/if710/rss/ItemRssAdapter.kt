package br.ufpe.cin.if710.rss

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.itemlista.view.*

class ItemRssAdapter(private val rssFeed: List<ItemRSS>) :
        RecyclerView.Adapter<ItemRssAdapter.MyViewHolder>() {

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var data: TextView? = null
        var titulo: TextView? = null

        init {
            this.data = item.item_data
            this.titulo = item.item_titulo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemRssAdapter.MyViewHolder {
        val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.itemlista, parent, false)
        item.setOnClickListener {
            Toast.makeText(item.context, item.item_titulo.text, Toast.LENGTH_SHORT).show()
        }
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val feedItem = rssFeed[position]
        holder.data?.text = feedItem.pubDate
        holder.titulo?.text = feedItem.title
    }

    override fun getItemCount() = rssFeed.size
}