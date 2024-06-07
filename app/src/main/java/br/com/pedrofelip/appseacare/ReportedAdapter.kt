package br.com.pedrofelip.appseacare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//CLASSE MOVIEADAPTER QUE ESTENTE RECYCLERVIEW. ADAPTER<MOVILE ADAPTER.MOVILE VIEW HOLDER>
class ReportedAdapter : RecyclerView.Adapter<ReportedAdapter.MovieViewHolder>() {
    //LISTA DE FILMES QUE SERÁ EXIBIDA NA RECYCLERVIEW
    private var reports: List<Reported> = emptyList()

    //MÉTODO CHAMADO QUANDO O VIEWHOLDER PRECISA SER CRIADO

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // INFLAR O LAYOUT DO ITEM_MOVIE.xml PARA CRIAR A VISUALIZADO DO INTEM DA LISTA
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reported, parent, false)
        return MovieViewHolder(view)

    }
    //MÉTODO CHAMADO PARA ATUALIZAR A VISUALIZACAO DE VIEWHOLDER COM DADOS ESPECIFICOS

    override fun onBindViewHolder(holder: ReportedAdapter.MovieViewHolder, position: Int) {
        // OBTEM O FILME NA POSIÇÃO ESPECIFICA DA LISTA DE FILMES
        val report = reports[position]
        // ATUALIZA A VISUALIÇÃO DO ViewHolder COM OS DADOS DO FILME
        holder.bind(report)
    }
    // MÉTODO CHAMADO PARA OBTER OS NÚMEROS TOTAL DE ITENS DA LISTA

    override fun getItemCount(): Int {
        return reports.size
    }

    //MÉTODO PERSONALIZADO PARA ATUALIZAR A LISTA DE FILMES E NOTIFICAR O RECYCLERVIEW

    fun setData(data: List<Reported>) {
        reports = data
        notifyDataSetChanged() //NOTIFICA O RECYCLERVIEW SOBRE AS MUDANÇAS DOS DADOS

    }

    //CLASSE INTERNA MovieViewHolder que estente RecyclerView.ViewHolder

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //REFERENCIAS AOS ELEMENTOS DE LAYOUT DO item_movie.xml
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val yearTextView : TextView = itemView.findViewById(R.id.yearTextView)

        //Método para vincular os dados do filme á vizualização ViewHolder

        fun bind(report: Reported) {

            titleTextView.text = report.description //Define o titulo do filme no TextView
            yearTextView.text = report.date.toString() //Define o ano de lançamento do filme no TextView
        }

    }
}