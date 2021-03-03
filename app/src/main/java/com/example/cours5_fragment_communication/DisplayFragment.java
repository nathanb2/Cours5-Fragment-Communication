package com.example.cours5_fragment_communication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    public static final String TAG = DisplayFragment.class.getSimpleName();
    private TextView mCounterTv;
    private SwitchCompat mEnableCounterUpdateSwitch;
    private DisplayFragmentListener mListener;

    public static DisplayFragment newInstance() {
        DisplayFragment displayFragment = new DisplayFragment();
        return displayFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DisplayFragmentListener){
            //context est l'instance de l'activity qui acceuil mon fragment
            //je le cast pour le reagrder uniquement selon l'aspect qu'a l'activity d'implementer l'interface
            //et donc en extrait que cette aspect que je met dans ma variable mListener
            //exemple de classe bonhome + interface capaciteDeCuisine
            //Je ne peux creer d'instance de l'interface , on ne peut prendre le concepte capacite de cuisine dasn la main (un bonhome oui!)
            //par contre je peux prendre un bonhome dans la main et ne garder que son aspect de bonhome qui a des capacites de cuisine
            mListener = (DisplayFragmentListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement DisplayFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVars(view);
        initListener();
        updateView(0);
    }

    public void updateView(int counter) {
        mCounterTv.setText(String.valueOf(counter));
    }

    private void initListener() {
        mEnableCounterUpdateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mListener.onStopClick(b);
            }
        });
    }

    private void initVars(View view) {
        mCounterTv = view.findViewById(R.id.FD_counter_tv);
        mEnableCounterUpdateSwitch = view.findViewById(R.id.FD_stop_switch);
    }

    /**
     * interface implemente dasn l'activity
     * et type de ma variable listener qui me permet d'appeler la fonction onStopClick implemente dasn l'activity
     */
    public interface DisplayFragmentListener{
        void onStopClick(boolean enableButtons);
    }
}
