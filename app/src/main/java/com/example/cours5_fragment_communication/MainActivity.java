package com.example.cours5_fragment_communication;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * J'implemente l' interface du fragments et sa methode
 * afin de pouvoire apppeler des fonctions dasn l'activity depuis le fragments via son interface
 */
public class MainActivity extends AppCompatActivity implements ActionFragment.ActionFragmentListener {

    private static final String IS_RECREATE_KEY = "IS_RECREATE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On ne cree explicitement les Fragments que si c'est la premiere fois que l'Activity est ouverte
        //Donc si recreattion apres rotation on ne creee pas explicitement les fragments, Android les recree tout seul
        //Le bundle a etait remplis dans onSaveInstanteState
        if (savedInstanceState == null || !savedInstanceState.containsKey(IS_RECREATE_KEY)
                || savedInstanceState.getInt(IS_RECREATE_KEY) == 0) {
            initActionFragment();
            initDisplayFragment();
        }
    }

    /**
     * Appele avant la destruction de l'activity
     * Save the status to know nin onCreate that this is an automatically recreate of the Activity
     * @param outState bundle saved when activity destroyed and automaticly recreated by Android (new Instance of the Activity) on rotation
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(IS_RECREATE_KEY, 1);
        super.onSaveInstanceState(outState);
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

}