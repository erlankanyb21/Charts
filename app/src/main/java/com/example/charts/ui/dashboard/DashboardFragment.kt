package com.example.charts.ui.dashboard

import CustomBottomSheetDialog
import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.charts.R
import com.example.charts.databinding.FragmentDashboardBinding
import com.example.charts.ui.home.base.BaseFragment
import com.example.charts.ui.home.model.AddContactDto
import com.example.charts.ui.home.model.AddContactResponse
import com.example.charts.ui.notifications.PreviewContactsAdapter

class DashboardFragment :
    BaseFragment<FragmentDashboardBinding, DashboardViewModel>(R.layout.fragment_dashboard) {

    private val adapter: DashboardListAdapter by lazy {
        DashboardListAdapter()
    }

    override val binding: FragmentDashboardBinding by viewBinding(FragmentDashboardBinding::bind)

    override val viewModel: DashboardViewModel by viewModels()

    override fun initialize() {
        val recyclerView = binding.recycler
        recyclerView.adapter = adapter

    }

    override fun constructListeners() {
        binding.btnSend.setOnClickListener {
            val customBottomSheetDialog = CustomBottomSheetDialog(requireContext(), adapter)
            customBottomSheetDialog.show()
        }

        binding.btnsave.setOnClickListener {
            val dataList: MutableList<Pair<String, String>> = mutableListOf()
            for (i in 0 until adapter.itemCount) {
                val (text, type) = adapter.getItemData(i)
                dataList.add(Pair(text, type!!))
            }

            for ((text, type) in dataList) {
               viewModel.getContacts(listOf(AddContactDto(type,text))).observe(viewLifecycleOwner) {
                   Toast.makeText(requireContext(), "${it.map { it.value }}", Toast.LENGTH_SHORT).show()
               }
            }
        }
    }
}