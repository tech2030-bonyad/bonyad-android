package fudex.bonyad.ui.Fragment.user

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.UserhomefragmentviewModelBinding
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.viewmodel.user.UserhomefragmentViewModel


class UserhomeFragment : Fragment() {
    lateinit var userhomefragmentViewModel: UserhomefragmentViewModel
    lateinit var binding: UserhomefragmentviewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_userhome,
                    container,
                    false
                )
                userhomefragmentViewModel = UserhomefragmentViewModel(this@UserhomeFragment)
                binding.model = userhomefragmentViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_userhome, container, false)
            userhomefragmentViewModel = UserhomefragmentViewModel(this@UserhomeFragment)
            binding.model = userhomefragmentViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        userhomefragmentViewModel.gethome()
        if (LoginSession.isLogin) {
            userhomefragmentViewModel.getnotscount()
            if (LoginSession.getUserData(activity).user.name!!.contains( " ") == true) {
                userhomefragmentViewModel.username.set(
                    getString(R.string.welcome) + " " + LoginSession.getUserData(
                        activity
                    ).user.name!!.substring(0,LoginSession.getUserData(
                        activity
                    ).user.name!!.indexOf(" "))
                )
            }else {
                userhomefragmentViewModel. username.set(
                    getString(R.string.welcome) + " " + LoginSession.getUserData(
                        activity
                    ).user.name!!
                )
            }
        }
        (activity as UserhomeActivity).userhomeViewModel.type.set(0)
        binding.root!!.isFocusableInTouchMode = true
        binding.root!!.requestFocus()
        binding.root!!.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                activity?.finish()
                return@OnKeyListener true
            }
            false
        })
    }

}