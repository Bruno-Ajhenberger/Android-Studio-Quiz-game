package com.example.myproject;

import java.util.ArrayList;
import java.util.List;

public interface OnClickInterface {
    void playButtonClicked();
    void exitButtonClicked();
    void updateButtonClicked();
    void saveButtonClicked(List<Boolean> checked);
}
