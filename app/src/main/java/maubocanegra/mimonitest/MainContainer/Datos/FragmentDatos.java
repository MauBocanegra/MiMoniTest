package maubocanegra.mimonitest.MainContainer.Datos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import maubocanegra.mimonitest.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDatos.OnFragmentDatosListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDatos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDatos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentDatosListener mListener;

    public FragmentDatos() {
        // Required empty public constructor
    }

    //New FragmentDatos
    public static FragmentDatos newInstance(String param1, String param2) {
        FragmentDatos fragment = new FragmentDatos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentDatos newInstance(){
        FragmentDatos fragment = new FragmentDatos();
        return fragment;
    }

    /* ----------------------------------------
     * ---------- FRAGMENT LIFECYCLE ----------
     * ---------------------------------------- */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentDatosListener) {
            mListener = (OnFragmentDatosListener) context;
            mListener.onFragmentInteraction(new String[]{"FragmentAttached!"});
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragment_datos, container, false);

        mRecyclerView = (RecyclerView) fragView.findViewById(R.id.recyclerView);

        //perfomance si es fijo el tamanio
        mRecyclerView.setHasFixedSize(true);

        //con linearlayout
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //adapter
        mAdapter = new DatosAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        // Regresamos la vista inflada
        return fragView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /* ---------------------------------
     * ---------- OWN METHODS ----------
     * --------------------------------- */

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String[] arr) {
        if (mListener != null) {
            mListener.onFragmentInteraction(arr);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentDatosListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String[] arr);
    }
}
