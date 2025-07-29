package fudex.bonyad.ui.Fragment.merchant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import fudex.bonyad.Model.FilterModel
import fudex.bonyad.Model.FilterorderModel
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.MerchantordersfragmentviewModelBinding
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import fudex.bonyad.viewmodel.merchant.OrdersListViewModel

class MerchantordersFragment : Fragment(), DialogListener {
    lateinit var ordersListViewModel: OrdersListViewModel
    lateinit var binding: MerchantordersfragmentviewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_merchantorders,
                    container,
                    false
                )
                ordersListViewModel = OrdersListViewModel(this@MerchantordersFragment)
                binding.model = ordersListViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_merchantorders, container, false)
            ordersListViewModel = OrdersListViewModel(this@MerchantordersFragment)
            binding.model = ordersListViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        ordersListViewModel.page = 1
        if (ordersListViewModel.status1 == 0){
            if (ordersListViewModel.status.get() == 1) {
                ordersListViewModel.statuses.add(1)
                ordersListViewModel.statuses.add(6)
                ordersListViewModel.statuses.add(7)
                ordersListViewModel.statuses.add(8)
            }else if (ordersListViewModel.status.get() == 3) {
                ordersListViewModel.statuses.add(4)
            }
        }else {
            ordersListViewModel.statuses.add(ordersListViewModel.status1 ?: 0)
        }
        ordersListViewModel.getorders()
        (activity as MerchanthomeActivity).merchanthomeViewModel.type.set(2)
    }

    override fun onDataReceived(data: String) {
        if (data.contains("filter")) {
            val filter = Gson().fromJson(data.replace("filter", ""), FilterorderModel::class.java)
            ordersListViewModel.statuses.clear()
            ordersListViewModel.from = filter.from ?: ""
            ordersListViewModel.to = filter.to ?: ""
            if (filter.status == 0){
                if (ordersListViewModel.status.get() == 1) {
                    ordersListViewModel.statuses.add(1)
                    ordersListViewModel.statuses.add(6)
                    ordersListViewModel.statuses.add(7)
                    ordersListViewModel.statuses.add(8)
                }else if (ordersListViewModel.status.get() == 3) {
                    ordersListViewModel.statuses.add(4)
                }
            }else {
                ordersListViewModel.statuses.add(filter.status ?: 0)
            }
            ordersListViewModel.page = 1
            ordersListViewModel.getorders()
        }
    }

}