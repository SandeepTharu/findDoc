package com.example.finddoc.models

class Doctor {
    var id = 0

    var name: String? = null

    var imageURL: String? = null

    var ratings: String? = null

    var distance: String? = null

    var email: String? = null

    var mobile: String? = null

    var city: String? = null

    constructor(id: Int, name: String, ratings:String, distance: String){
        this.id = id
        this.name = name
        this.ratings = ratings
        this.distance = distance
    }

    constructor(id: Int,imageUrl: String, name: String, ratings:String, distance: String){
        this.id = id
        this.name = name
        this.imageURL = imageUrl
        this.ratings = ratings
        this.distance = distance
    }

    constructor(id: Int,imageUrl: String, name: String, ratings:String, distance: String, email: String, mobile: String, city:String){
        this.id = id
        this.name = name
        this.imageURL = imageUrl
        this.ratings = ratings
        this.distance = distance
        this.email = email
        this.mobile = mobile
        this.city = city
    }

    constructor(id: Int, name: String){
        this.id = id
        this.name = name
    }
}