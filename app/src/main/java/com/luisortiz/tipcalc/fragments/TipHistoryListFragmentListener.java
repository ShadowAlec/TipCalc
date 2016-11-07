package com.luisortiz.tipcalc.fragments;

import com.luisortiz.tipcalc.entity.TipRecord;

/**
 * Created by luis on 10/10/16.
 */

public interface TipHistoryListFragmentListener {

    void addToList(TipRecord record);
    void clearList();
}
