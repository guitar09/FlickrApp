package com.higor.search.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.higor.search.app.di.SearchKoinComponent
import com.higor.search.app.navigation.SearchNavigationExternalRoutesProvider
import org.koin.core.component.inject

internal abstract class BaseActivityViewBinding<ViewBindingType : ViewBinding> :
    AppCompatActivity(), SearchKoinComponent {

    private var _binding: ViewBindingType? = null
    protected val binding get() = requireNotNull(_binding)
    val routesProvider: SearchNavigationExternalRoutesProvider by inject()

    abstract fun setupViewBinding(inflater: LayoutInflater): ViewBindingType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setupViewBinding(layoutInflater)
        _binding?.let { setContentView(it.root) }
    }
}
