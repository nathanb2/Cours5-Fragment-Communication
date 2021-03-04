package com.example.cours5_fragment_communication;

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

import org.greenrobot.eventbus.EventBus;

public class DisplayFragment extends Fragment {

    public static final String TAG = DisplayFragment.class.getSimpleName();
    private static final String COUNTER_KEY = "COUNTER_KEY";
    private TextView mCounterTv;
    private SwitchCompat mEnableCounterUpdateSwitch;

    public static DisplayFragment newInstance() {
        DisplayFragment displayFragment = new DisplayFragment();
        return displayFragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(COUNTER_KEY, Integer.parseInt(mCounterTv.getText().toString()));
        super.onSaveInstanceState(outState);
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
        int counter = 0;
        if (savedInstanceState != null && savedInstanceState.containsKey(COUNTER_KEY)){
            counter = savedInstanceState.getInt(COUNTER_KEY);
        }
        updateView(counter);
    }

    public void updateView(int counter) {
        mCounterTv.setText(String.valueOf(counter));
    }

    private void initListener() {
        mEnableCounterUpdateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //je Post un evenemnt eventbus en lui passant en parametre une instance de EnableButtonsEvent
                // les fonctions qui recoivent en parametre un objet de type EnableButtonsEvent
                // et qui se trouve dans une activity ou un fragment qui est a l'ecoute de eventBus (register dans onStart)
                //seront appelees et recevront cette instance de EnableButtonsEvent en parametre (et donc galement b)
                EventBus.getDefault().post(new EnableButtonsEvent(b));
            }
        });
    }

    private void initVars(View view) {
        mCounterTv = view.findViewById(R.id.FD_counter_tv);
        mEnableCounterUpdateSwitch = view.findViewById(R.id.FD_stop_switch);
    }

}
