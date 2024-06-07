package br.com.pedrofelip.appseacare

import com.google.gson.annotations.SerializedName

//CLASSE MOVIE RESPONSE QUE RECEBE A RESPOSTA DA API COM UMA LISTA DE FILMES
data class ReportedResponse (
    //ANOTAÇÃO QUE MAPEIA O CAMPO "MOVIES" DA RESPOSTA JSON PARA ALISTA DE FILMES

    @SerializedName("reports")
    val reports : List<Reported>
    )