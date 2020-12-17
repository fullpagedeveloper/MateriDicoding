package com.fullpagedeveloper.githubuserapp.model

data class Users(
    val username: String,
    val name: String,
    val avatar: String,
    val company: String,
    val location: String,
    val repository: Int,
    val follower: Int,
    val following: Int
)

/*class MyModel {
    @SerializedName("list")
    var list: ArrayList<MyObject>? = null

    class MyObject {
        @SerializedName("name")
        var name: String? = null

        @SerializedName("age")
        var age = 0
    }
}*/
