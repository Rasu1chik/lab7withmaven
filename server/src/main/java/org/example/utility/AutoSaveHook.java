package org.example.utility;

import org.example.managers.FileManager;

public class AutoSaveHook implements Runnable {


    private final FileManager fileManager;

    public AutoSaveHook(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        fileManager.saveObjects();
    }
}
