package org.example.managers;

public class DatabaseCommands {
    public static final String allTablesCreation = """
            CREATE TYPE FURNISH AS ENUM (
                'DESIGNER',
                'NONE',
                'FINE',
                'BAD',
                'LITTLE'
            );
            CREATE TYPE VIEW AS ENUM (
                'STREET',
                'BAD',
                'NORMAL',
                'GOOD',
                'TERRIBLE'
            );
            CREATE TYPE TRANSPORT AS ENUM (
                'FEW',
                'NONE',
                'LITTLE',
                'NORMAL',
                'ENOUGH'
            );
            
            CREATE TABLE IF NOT EXISTS flat (
                id SERIAL PRIMARY KEY,
                name TEXT NOT NULL,
                cord_x DOUBLE PRECISION NOT NULL,
                cord_y DOUBLE PRECISION NOT NULL,
                creation_date TIMESTAMP NOT NULL,
                area DOUBLE PRECISION NOT NULL,
                numberOfRooms BIGINT NOT NULL,
                furnish FURNISH NOT NULL,
                view VIEW NOT NULL,
                transport TRANSPORT NOT NULL,
                house_name TEXT NOT NULL,
                year BIGINT NOT NULL,
                numberOfFloors BIGINT NOT NULL,
                numberOfFlatsOnFloor BIGINT NOT NULL,
                owner_login TEXT NOT NULL
            );
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                login TEXT NOT NULL,
                password TEXT NOT NULL,
                salt TEXT NOT NULL
            );
            """;

    public static final String addObject = """
            INSERT INTO flat (name, cord_x, cord_y, creation_date, area, numberOfRooms, furnish, view, transport, house_name, year, numberOfFloors, numberOfFlatsOnFloor, owner_login)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id;
            """;

    public static final String updateUserObject = """
            UPDATE flat
            SET name = ?, cord_x = ?, cord_y = ?, creation_date = ?, area = ?, numberOfRooms = ?, furnish = ?, view = ?, transport = ?, house_name = ?, year = ?, numberOfFloors = ?, numberOfFlatsOnFloor = ?
            WHERE id = ? AND owner_login = ?
            RETURNING id;
            """;

    public static final String getAllObjects = """
            SELECT * FROM flat;
            """;

    public static final String addUser = """
            INSERT INTO users (login, password, salt) VALUES (?, ?, ?);
            """;

    public static final String getUser = """
            SELECT * FROM users WHERE login = ?;
            """;

    public static final String deleteUserOwnedObjects = """
            DELETE FROM flat WHERE owner_login = ? AND id = ? RETURNING id;
            """;

    public static final String deleteUserObject = """
            DELETE FROM flat WHERE owner_login = ? AND id = ? RETURNING id;
            """;
}
