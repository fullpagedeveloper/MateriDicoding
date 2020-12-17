package com.fullpagedeveloper.myunittesting.model

class CuboidModel {
    private var width: Double = 0.0
    private var lenght: Double = 0.0
    private var height: Double = 0.0

    fun getVolume(): Double = width * lenght * height

    fun getSurfaceArea(): Double {
        val wl = width * lenght
        val wh = width * height
        val lh = width * height

        return 2 * (wl + wh + lh)
    }

    fun getCircumeference(): Double = 4 * (width + lenght + height)

    fun save(width: Double, lenght: Double, height: Double){
        this.width = width
        this.lenght = lenght
        this.height = height
    }
}