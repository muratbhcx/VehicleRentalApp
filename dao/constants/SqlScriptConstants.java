package Vehicle_rental_app.dao.constants;

public class SqlScriptConstants {

    public SqlScriptConstants() {
    }

    public static final String CUSTOMER_SAVE = """
                INSERT INTO customer(name, email, password, age, role) VALUES (?, ?, ?, ?, ?)
            """;

    public static final String CUSTOMER_EXIST_BY_EMAIL = """
                    SELECT * FROM customer WHERE email = ?
            """;

    public static final String RENT_SAVE = """
            INSERT INTO rent (customer_id, vehicle_brand, vehicle_model, total_amount)
            VALUES (?, ?, ?, ?)
            """;

    public static final String VEHICLE_SEARCH_BY_NAME = """
            SELECT v.id as id,
                   v.brand as brand,
                   v.model as model,
                   v.year as year,
                   v.hourly_rental_price as hourly_rental_price,
                   v.price as price,
                   c.id as category_id,
                   c.name as category_name
                   FROM vehicle v LEFT JOIN public.category c on c.id = v.category_id
                   WHERE v.brand LIKE ?
            """;

    public static final String VEHICLE_SAVE = """
            INSERT INTO vehicle (brand, model, year, hourly_rental_price, price, category_id)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    public static final String VEHICLE_FIND_ALL = """
            SELECT v.id           as id,
                   v.brand         as brand,
                   v.model        as model,
                   v.year         as year,
                   v.hourly_rental_price as hourly_rental_price,
                   v.price        as price,
                   c.id           as category_id,
                   c.name         as category_name
            FROM vehicle v,
                 category c
            WHERE v.category_id = c."id"
            ORDER BY v.id ASC
            LIMIT ? OFFSET ?
            """;

    public static final String RENT_FIND_BY_CUSTOMER_ID = """
            SELECT * FROM rent WHERE customer_id = ?
            """;

    public static final String VEHICLE_TOTAL_PAGE_COUNT = """
            SELECT COUNT (*) FROM vehicle
            """;

    public static final String VEHICLE_FIND_BY_CATEGORY_NAME = """
            SELECT v.id as id,
                   v.brand as brand,
                   v.model as model,
                   v.year as year,
                   v.hourly_rental_price as hourly_rental_price,
                   v.price as price,
                   c.id as category_id,
                   c.name as category_name
                   FROM vehicle v 
                   JOIN category c on c.id = v.category_id
                   WHERE c.name = ? ORDER BY v.id ASC
                   LIMIT ? OFFSET ?
            """;

    public static final String VEHICLE_FIND_BY_NAME = """
            SELECT v.id,
            v.brand,
            v.model,
            v.year,
            v.hourly_rental_price,
            v.price, v.category_id, c.name AS category_name
            FROM vehicle v
            JOIN category c ON v.category_id = c.id
            WHERE v.brand = ?  AND v.model = ?
            """;

    public static final String USER_SAVE = """
            INSERT INTO users (username, password, active) 
            VALUES (?, ?, ?)
            """;

    public static final String USER_FIND_BY_NAME = """
            SELECT * FROM users  WHERE username = ?
            """;

    public static final String CATEGORY_SAVE = """
            INSERT INTO category (name, created_by, updated_by)
            VALUES (?, ?, ?)
            """;

    public static final String CATEGORY_FIND_BY_ID = """
            SELECT *  FROM category WHERE id = ?
            """;
}
