package com.vilocorp.appdemo.objetos

data class FireBaseItems(
    var items: MutableList<FireBaseItem>
)

data class FireBaseItem(
    var firebasescreen: String,
    var firebase_tipo: String,
    var tituloselcolor: String,
    var tipo: String,
    var imagetv: String,
    var private: String,
    var programa: String,
    var imagen: String,
    var firebase_screen: String,
    var titulounselcolor: String
)

data class DataProgram(
    var data: List<ProgramItem>
)

data class ProgramItem(
    var url: String,
    var title: String
)