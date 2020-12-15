package com.rls.pickfile.android.utils;

import com.rls.pickfile.android.helper.FileFilter;

import java.io.File;

public class HiddenFilter implements FileFilter {

    @Override
    public boolean accept(File f) {
        return !f.isHidden();
    }
}
