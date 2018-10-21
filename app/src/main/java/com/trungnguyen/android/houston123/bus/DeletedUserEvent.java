package com.trungnguyen.android.houston123.bus;

/**
 * Created by trungnd4 on 21/10/2018.
 */
public class DeletedUserEvent {
    private int userPosition;

    public DeletedUserEvent(int userPosition) {
        this.userPosition = userPosition;
    }

    public int getUserPosition() {
        return userPosition;
    }
}
