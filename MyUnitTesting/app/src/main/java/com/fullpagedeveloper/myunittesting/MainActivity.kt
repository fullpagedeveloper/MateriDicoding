package com.fullpagedeveloper.myunittesting

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fullpagedeveloper.myunittesting.model.CuboidModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        btn_save.setOnClickListener(this)
        btn_calculator_surface_area.setOnClickListener(this)
        btn_calculator_circumference.setOnClickListener(this)
        btn_calculator_volume.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val lenght = edt_lenght.text.toString().trim()
        val width = edt_width.text.toString().trim()
        val height = edt_height.text.toString().trim()

        when {
            lenght.isEmpty() -> edt_lenght.error = "Field ini tidak boleh kosong"
            width.isEmpty() -> edt_width.error = "Field ini tidak boleh kosong"
            height.isEmpty() -> edt_height.error = "Field ini tidak boleh kosong"

            else -> {
                val l = lenght.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()


                when (v?.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        group.visibility = View.VISIBLE
                        btn_save.visibility = View.GONE
                    }
                    R.id.btn_calculator_circumference -> {
                        tv_result.text = mainViewModel.getCircumference().toString()
                        group.visibility = View.GONE
                        btn_save.visibility = View.VISIBLE
                    }
                    R.id.btn_calculator_surface_area -> {
                        tv_result.text = mainViewModel.getSurfaceArea().toString()
                        group.visibility = View.GONE
                        btn_save.visibility = View.VISIBLE
                    }
                    R.id.btn_calculator_volume -> {
                        tv_result.text = mainViewModel.getVolume().toString()
                        group.visibility = View.GONE
                        btn_save.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}