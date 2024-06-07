package br.com.pedrofelip.appseacare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrofelip.appseacare.ReportedAdapter
import br.com.pedrofelip.appseacare.ReportedResponse
import br.com.pedrofelip.appseacare.ReportedService
import br.com.pedrofelip.appseacare.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var adapter: ReportedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando as views do layout
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.errorTextView)

        // Inicializando o adapter e configurando o RecyclerView
        adapter = ReportedAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Chamando o método para carregar os dados
        fetchData()
    }

    private fun fetchData() {
        // Configurando o Retrofit para fazer requisições HTTP
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/report") // URL base da API https://reactnative.dev/movies.json
            .addConverterFactory(GsonConverterFactory.create()) // Converte JSON em objetos Kotlin
            .build()

        // Criando o serviço com a interface MovieService
        val service = retrofit.create(ReportedService::class.java)
        val call = service.getReporteds() // Chamada para obter os filmes

        progressBar.visibility = View.VISIBLE // Exibindo a ProgressBar

        // Executando a chamada assincronamente
        call.enqueue(object : Callback<ReportedResponse> {
            override fun onResponse(call: Call<ReportedResponse>, response: Response<ReportedResponse>) {
                progressBar.visibility = View.GONE // Escondendo a ProgressBar

                if (response.isSuccessful) { // Verificando se a resposta foi bem-sucedida
                    val reports = response.body()?.reports // Obtendo a lista de filmes da resposta
                    if (reports != null) {
                        adapter.setData(reports) // Atualizando o RecyclerView com os dados obtidos
                    } else {
                        showError("Nenhum filme encontrado.") // Exibindo mensagem de erro se a lista estiver vazia
                    }
                } else {
                    showError("Erro na requisição: ${response.code()} - ${response.message()}") // Exibindo mensagem de erro da requisição
                }
            }

            override fun onFailure(call: Call<ReportedResponse>, t: Throwable) {
                progressBar.visibility = View.GONE // Escondendo a ProgressBar
                showError("Erro ao obter os dados: ${t.message}") // Exibindo mensagem de erro de falha na requisição
            }
        })
    }

    private fun showError(message: String) {
        errorTextView.visibility = View.VISIBLE // Exibindo a TextView de erro
        errorTextView.text = message // Configurando o texto de erro
    }
}