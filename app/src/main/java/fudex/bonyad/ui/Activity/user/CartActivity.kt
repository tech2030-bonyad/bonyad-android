package fudex.bonyad.ui.Activity.user

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.CartListviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.ui.Activity.user.AddressesActivity
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity
import fudex.bonyad.viewmodel.user.CartListViewModel
import java.io.IOException
import java.util.Locale

class CartActivity : BaseActivity() , DialogListener{
    lateinit var cartListViewModel: CartListViewModel
    lateinit var binding : CartListviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        cartListViewModel = CartListViewModel(this@CartActivity)
        binding.model = cartListViewModel
    }

    override fun onResume() {
        super.onResume()
        cartListViewModel.getcarts()
        cartListViewModel.getbalance()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK) {
            cartListViewModel.lat = data?.getStringExtra("lat") ?: ""
            cartListViewModel.lng = data?.getStringExtra("lng") ?: ""
            val geocoder = Geocoder(this@CartActivity, Locale.getDefault())
            var address = "Unknown location"
            try {
                val addresses = geocoder.getFromLocation(cartListViewModel.lat.toDouble() , cartListViewModel.lng.toDouble(), 1)
                if (addresses!!.isNotEmpty()) {
                    address = addresses[0].getAddressLine(0)
                    cartListViewModel.addressname.set(address)
                    cartListViewModel.notifyChange()
                }
            } catch (e: IOException) {
                // Handle any errors that occur during the geocoding process
            }
        }
    }
    override fun onDataReceived(data: String) {
        if (data.contains("deletecart")){
            cartListViewModel.deletecart()
        }
    }
}