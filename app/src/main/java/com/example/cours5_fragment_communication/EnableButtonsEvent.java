package com.example.cours5_fragment_communication;

/**
 * class servant d'evenement eventBus
 */
public class EnableButtonsEvent {

    private boolean mEnableButtons;

    public EnableButtonsEvent(boolean enableButtons){
        mEnableButtons = enableButtons;
    }

    public boolean isEnableButtons() {
        return mEnableButtons;
    }
}
