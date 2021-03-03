package com.example.cours5_fragment_communication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * J'implemente les  interfaces de chacun de mes fragments et leurs methodes
 * afin de pouvoire apppeler des fonctions dasn l'activity depuis les fragments via leur interface
 */
public class MainActivity extends AppCompatActivity implements ActionFragment.ActionFragmentListener
        , DisplayFragment.DisplayFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionFragment();
        initDisplayFragment();
    }

    public void initDisplayFragment() {
        DisplayFragment displayFragment = DisplayFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.AM_top_container, displayFragment, DisplayFragment.TAG).addToBackStack(DisplayFragment.TAG).commit();
    }

    private void initActionFragment() {
        ActionFragment actionFragment = ActionFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.AM_bottom_container, actionFragment, ActionFragment.TAG).addToBackStack(ActionFragment.TAG).commit();
    }

    private void displayCounterInFragment(int counter) {
        DisplayFragment displayFragment = ((DisplayFragment) getSupportFragmentManager().findFragmentByTag(DisplayFragment.TAG));
        if (displayFragment != null) {
            displayFragment.updateView(counter);
        }
    }

    /**
     * Fonction appele depuis ActionFragment via mlistener.onCounterChanged(counter)
     * @param counter parametre recu depuis ActionFragment
     */
    @Override
    public void onCounterChanged(int counter) {
        displayCounterInFragment(counter);
    }

    /**
     * fonction appele depuis DisplayFragment via mListener.onstopClick(b)
     * @param enableButtons parametre recu de DisplayFragment
     */
    @Override
    public void onStopClick(boolean enableButtons) {
        ActionFragment actionFragment = ((ActionFragment) getSupportFragmentManager().findFragmentByTag(ActionFragment.TAG));
        if (actionFragment != null){
            actionFragment.enableActionBtns(enableButtons);
        }
    }

}