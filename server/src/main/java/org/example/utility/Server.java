package org.example.utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.App;
import org.example.managers.CommandManager;
import org.example.managers.ConnectionManager;
import org.example.managers.DatabaseManager;
import org.example.managers.PoolManager;

public class Server {
    private final int port;
    private final CommandManager commandManager;


    public static final Logger rootLogger = LogManager.getLogger(App.class);

    private final DatabaseManager databaseManager;

    public Server(CommandManager commandManager, DatabaseManager databaseManager) {
        this.port = App.PORT;
        this.commandManager = commandManager;
        this.databaseManager = databaseManager;
    }

    public void run() {



        rootLogger.info("(_!_)(_!_)(_!_)(_!_)(_!_)СЕРВЕР ЗАПУЩЕН(_!_)(_!_)(_!_)(_!_)(_!_)");


        while (true) {
            PoolManager.checkAllFutures();
            Thread connectionThread = new Thread(new ConnectionManager(commandManager, databaseManager, port));
            connectionThread.start();

        }
    }


}
