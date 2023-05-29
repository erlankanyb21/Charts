package com.example.charts.ui.notifications

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.charts.R
import com.example.charts.databinding.FragmentNotificationsBinding
import com.example.charts.ui.dashboard.DashboardViewModel
import com.example.charts.ui.home.HomeViewModel
import com.example.charts.ui.home.base.BaseFragment
import com.example.charts.ui.home.model.AddContactResponse

class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding, DashboardViewModel>(R.layout.fragment_notifications) {
    override val binding by viewBinding(FragmentNotificationsBinding::bind)

    override val viewModel: DashboardViewModel by viewModels()

    private val previewContactsAdapter: PreviewContactsAdapter by lazy {
        PreviewContactsAdapter(this::onItemClick)
    }

    private fun onItemClick(addContactResponse: AddContactResponse) {

    }

    override fun initialize() {
        binding.recycler.adapter = previewContactsAdapter

    }

    override fun launchObservers() {

    }

}