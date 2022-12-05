package cucerdariancatalin.marvel.view.ui.main

import android.os.Bundle
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import cucerdariancatalin.marvel.R
import cucerdariancatalin.marvel.databinding.ActivityMainBinding
import cucerdariancatalin.marvel.view.adapter.PosterAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            adapter = PosterAdapter()
            vm = getViewModel()
        }
    }
}