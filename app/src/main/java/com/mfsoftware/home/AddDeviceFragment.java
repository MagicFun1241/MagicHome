package com.mfsoftware.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfsoftware.home.adapters.Category;
import com.mfsoftware.home.adapters.DataAdapter;
import com.mfsoftware.home.decorators.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDeviceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDeviceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDeviceFragment extends DialogFragment {

    private OnFragmentInteractionListener mListener;

    public AddDeviceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddDeviceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDeviceFragment newInstance() {
        return new AddDeviceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.categoryList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        ArrayList<Category> list = new ArrayList<>();
        list.add(new Category(getString(R.string.category_tv), R.drawable.ic_tv));
        list.add(new Category(getString(R.string.category_wifi), R.drawable.ic_wifi));
        list.add(new Category(getString(R.string.category_locks), R.drawable.ic_lock_outline));
        list.add(new Category(getString(R.string.category_music), R.drawable.ic_music_note));

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 50, true));
        recyclerView.setAdapter(new DataAdapter(getContext(), list));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_device, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) mListener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
