package com.cursoapp.skyblueapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoPacoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoPacoteFragment extends Fragment {

    public InfoPacoteFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InfoPacoteFragment newInstance(String param1, String param2) {
        InfoPacoteFragment fragment = new InfoPacoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_pacote, container, false);
    }
}
