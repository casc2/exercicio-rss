package br.ufpe.cin.if710.rss

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.itemlista.view.*
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast

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
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val feedItem = rssFeed[position]

        holder.data?.text = feedItem.pubDate
        holder.titulo?.text = feedItem.title

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(feedItem.link))
            intent.addCategory(CATEGORY_DEFAULT)
            intent.addCategory(CATEGORY_BROWSABLE)
            if (intent.resolveActivity(holder.itemView.context.packageManager) != null) {
                startActivity(holder.itemView.context, intent, null)
            } else {
                Toast.makeText(holder.itemView.context,
                        "Não foi possível abrir o link: navegador compatível não encontrado",
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount() = rssFeed.size
}