package com.example.pmdmrecu2

class Libro {
    var titulo: String = ""
    var paginas: Int = 0
    var autor: String = ""
    var year: Int = 0
    var libro = ArrayList<Libro>()

    constructor(titulo: String, paginas: Int, autor: String, year: Int) {
        this.titulo = titulo
        this.paginas = paginas
        this.autor = autor
        this.year = year
    }

    constructor()


}