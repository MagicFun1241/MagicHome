package com.mfsoftware.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mfsoftware.home.api.Api
import com.mfsoftware.home.api.GetDevicesResponse
import com.mfsoftware.home.data.model.LoggedInUser
import com.mfsoftware.home.models.Device
import com.mfsoftware.home.models.Room
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var mListener: OnFragmentInteractionListener? = null
    private var mUser: LoggedInUser? = null
    private var mDevicesList: List<Device>? = null
    private var mRoomsList: List<Room>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.setActionBar(home_toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.item_notifications) {
            Toast.makeText(context, "N", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val context = context!!

        add_button.setOnClickListener { startActivity(Intent(context, AddDeviceActivity::class.java)) }

        home_refresh.setOnRefreshListener {
            val call = Api.json.getDevices(Api.getFingerPrint(), Api.getAuthorizationHeader())
            call?.enqueue(object : Callback<GetDevicesResponse?> {
                override fun onResponse(call: Call<GetDevicesResponse?>, response: Response<GetDevicesResponse?>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Ok", Toast.LENGTH_LONG).show()
                    } else Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<GetDevicesResponse?>, t: Throwable) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
            })
        }

        welcome_first_name.text = mUser!!.firstName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun onButtonPressed(uri: Uri?) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri?)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance(user: LoggedInUser?): HomeFragment {
            val fragment = HomeFragment()
            fragment.mUser = user
            // TODO: Получать результаты с сервера
            //fragment.mDevicesList = devices;
            //fragment.mRoomsList = rooms;
            return fragment
        }

        @JvmStatic
        fun newInstance(user: LoggedInUser, devices: MutableList<Device>, rooms: MutableList<Room>): Fragment {
            val fragment = HomeFragment()
            fragment.mUser = user
            fragment.mDevicesList = devices
            fragment.mRoomsList = rooms
            return fragment
        }
    }
}