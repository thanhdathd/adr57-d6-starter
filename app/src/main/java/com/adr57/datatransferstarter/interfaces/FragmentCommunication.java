package com.adr57.datatransferstarter.interfaces;

import com.adr57.datatransferstarter.model.User;

public interface FragmentCommunication {
    void onMessageSent(String message);
    void onUserSelected(User user);
    String onRequestData();
}
