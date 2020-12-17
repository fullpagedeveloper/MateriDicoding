package com.fullpagedeveloper.myunittesting

import com.fullpagedeveloper.myunittesting.model.CuboidModel

class MainViewModel(private val cuboidModel: CuboidModel) {

    fun getCircumference(): Double = cuboidModel.getCircumeference()

    fun getSurfaceArea(): Double = cuboidModel.getSurfaceArea()

    fun getVolume(): Double = cuboidModel.getVolume()

    fun save(l: Double, w: Double, h: Double){
        cuboidModel.save(l, w, h)
    }
}