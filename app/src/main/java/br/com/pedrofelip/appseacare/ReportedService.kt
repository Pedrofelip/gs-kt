package br.com.pedrofelip.appseacare

import retrofit2.Call
import retrofit2.http.GET

// INTERFACE MOVIE SERVICE QUE DEFINE AS OPERACOES DA API PARA OBETER FILMES
interface ReportedService {
    //Método GET para obter a lista de filmes da API
    @GET("reports.json")
    fun getReporteds(): Call<ReportedResponse>
}


