package fudex.bonyad.ui.Fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.UserorderslistfragmentviewModelBinding
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import fudex.bonyad.viewmodel.user.UserordersListViewModel

class UserordersFragment : Fragment() {

    lateinit var userordersListViewModel: UserordersListViewModel
    lateinit var binding: UserorderslistfragmentviewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_userorders,
                    container,
                    false
                )
                userordersListViewModel = UserordersListViewModel(this@UserordersFragment)
                binding.model = userordersListViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_userorders, container, false)
            userordersListViewModel = UserordersListViewModel(this@UserordersFragment)
            binding.model = userordersListViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        userordersListViewModel.page = 1
        userordersListViewModel.getorders()
        (activity as UserhomeActivity).userhomeViewModel.type.set(2)
    }

}