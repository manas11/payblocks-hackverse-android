package com.hackverse.hackverse.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hackverse.hackverse.R
import com.hackverse.hackverse.data.network.MyApi
import com.hackverse.hackverse.data.network.NetworkConnectionInterceptor
import com.hackverse.hackverse.data.network.responses.MedicineSetup
import com.hackverse.hackverse.data.network.responses.PaymentSetup
import com.hackverse.hackverse.data.repository.MedicineRepository
import com.hackverse.hackverse.data.repository.PaymentRepository
import com.hackverse.hackverse.databinding.FragmentAmountSetupBinding
import com.hackverse.hackverse.databinding.FragmentMedicineBinding
import com.hackverse.hackverse.utils.hide
import com.hackverse.hackverse.utils.show
import com.hackverse.hackverse.utils.toast
import kotlinx.android.synthetic.main.fragment_amount_setup.*

class MedicineFragment  : Fragment(), MedicineListener, MedicineSetupClickListener {

    private var viewModel: MedicineViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val networkConnectionInterceptor = NetworkConnectionInterceptor(activity)
        val api = MyApi(networkConnectionInterceptor)
        val repository = MedicineRepository(api)
        val factory = MedicineViewModelFactory(repository, this)

        val dataBinding = DataBindingUtil.inflate<FragmentMedicineBinding>(
            inflater,
            R.layout.fragment_medicine,
            container,
            false
        )



        viewModel = ViewModelProviders.of(this, factory).get(MedicineViewModel::class.java)
        dataBinding.medicineViewModel = viewModel
        viewModel!!.paymentListener = this
        viewModel!!.onCameraClickListener = this

        return dataBinding.root


    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        context.toast(message)
    }

    override fun onSuccess(medicineSetup: MedicineSetup) {
        progress_bar.hide()
        Log.v("success", medicineSetup.medicines)
//        activity.toast("Paid successfully to " + paymentSetup.name)
        Intent(activity, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onCameraButtonClick(view: View) {

        ImagePicker.with(this)
            .cameraOnly()
            .crop(
                1f,
                1f
            )                    //Crop image(Optional), Check Customization for more option
            .compress(150)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                512,
                360
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel?.onActivityResult(requestCode, resultCode, data)
    }


}