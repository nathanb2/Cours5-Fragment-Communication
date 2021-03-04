package com.example.cours5_fragment_communication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ActionFragment extends Fragment {

    public static final String TAG = ActionFragment.class.getSimpleName();
    private static final String COUNTER_KEY = "COUNTER_KEY";
    private Button mMinusBtn;
    private Button mPlusBtn;
    private int counter;
    private ActionFragmentListener mListener;

    public static ActionFragment newInstance() {
        ActionFragment actionFragment = new ActionFragment();
        return actionFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //context est l'instance de l'activity qui acceuil mon fragment
        //je le cast pour le reagrder uniquement selon l'aspect qu'a l'activity d'implementer l'interface
        //et donc en extrait que cette aspect que je met dans ma variable mListener
        //exemple de classe bonhome + interface capaciteDeCuisine
        //Je ne peux creer d'instance de l'interface , on ne peut prendre le concepte capacite de cuisine dasn la main (un bonhome oui!)
        //par contre je peux prendre un bonhome dans la main et ne garder que son aspect de bonhome qui a des capacites de cuisine
        if (context instanceof ActionFragmentListener){
            mListener = (ActionFragmentListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement ActionFragmentListener");
        }
    }

    /**
     * indique au fragment qu'il doit etre a l'ecoute des evenement post avec eventbus dasn toute l'application
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * indique au fragment d'arreter d'etre a l'ecoute des evenement post avec eventbus
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(COUNTER_KEY)) {
            counter = savedInstanceState.getInt(COUNTER_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(COUNTER_KEY, counter);
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_action, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVars(view);
        initListener();

    }

    private void initListener() {
        mPlusBtn.setOnClickListener(view -> {
            mListener.onCounterChanged(++counter);
        });

        mMinusBtn.setOnClickListener(view -> {
            mListener.onCounterChanged(--counter);
        });
    }

    private void initVars(View view) {
        mMinusBtn = view.findViewById(R.id.FA_minus_btn);
        mPlusBtn = view.findViewById(R.id.FA_plus_btn);
    }

    /**
     * Subscribe indique que cette fonction [peut etre appele par eventBus
     * Fonction pouvant etre appele par un post eventbus
     * @param enableButtons de type EnableButtonsEvent qui est l'evenement post quand l'on veut appeler cette fonction avec eventBus
     *                      C'est parceque la fonction recoit ce parametre (de ce type) qu'elle est appele quand un evenement est post avec une instance de cette evenement en parametre
     */
    @Subscribe
    public void enableActionBtns(EnableButtonsEvent enableButtons){
        mMinusBtn.setEnabled(enableButtons.isEnableButtons());
        mPlusBtn.setEnabled(enableButtons.isEnableButtons());
    }

    /**
     * interface implement dans l'activity
     * et type de ma variable listener qui me permet d'appeler la fonction onCounterChanged implemente dasn l'activity
     */
    public interface ActionFragmentListener{
        void onCounterChanged(int counter);
    }
}
