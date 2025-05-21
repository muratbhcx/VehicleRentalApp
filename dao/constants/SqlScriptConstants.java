package Vehicle_rental_app.dao.constants;

public class SqlScriptConstants {

    public SqlScriptConstants() {
    }

    public static final String CUSTOMER_SAVE = """
                INSERT INTO customer(name, email, password, age, role) VALUES (?, ?, ?, ?, ?)
            """;
    public static final String CUSTOMER_FIND_BY_ID = """
            SELECT * FROM customer WHERE id = ?
            """;
    public static final String CUSTOMER_FIND_ALL = """
                    SELECT * FROM customer
            """;
    public static final String CUSTOMER_EXIST_BY_EMAIL = """
                    SELECT * FROM customer WHERE email = ?
            """;
    public static final String PAYMENT_SAVE = """
            INSERT INTO payment (rent_id, payment_method, amount) 
            VALUES (?, ?, ?)
            """;
    public static final String RENT_SAVE = """
            INSERT INTO rent (customer_id, rent_date, total_amount, created_date, updated_date) )
            VALUES (?, ?, ?, ?, ?)
            """;
    public static final String RENT_ITEMS_SAVE = """
            INSERT INTO rent_item (rent_id, vehicle_id, price)
            VALUES (?, ?, ?)
            """;
    public static final String VEHICLE_SEARCH_BY_NAME = """
            SELECT v.id as id,
                   v.name as brand,
                   v.model as model,
                   v.year as year,
                   v.rental_price as rental_price,
                   v.price as price,
                   c.id as category_id,
                   c.name as category_name,
                   FROM vehicle v LEFT JOIN public.category c on c.id = v.category_id
                   WHERE v.name LIKE ?
            """;
    public static final String VEHICLE_SAVE = """
            INSERT INTO vehicle (brand, model, year, rental_price, price, category_id, created_by, updated_by)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
    public static final String VEHICLE_FIND_ALL = """
            SELECT v.id as id,
                   v.name as brand,
                   v.model as model,
                   v.year as year,
                   v.rental_price as rental_price,
                   v.price as price,
                   c.id as category_id,
                   c.name as category_name,
                   FROM vehicle v, category c WHERE v.category_id = c.category_id
                   ORDER BY v.id ASC
                   LIMIT ? OFFSET ?
            """;
    public static final String VEHICLE_FIND_BY_ID = """
            SELECT * FROM vehicle WHERE id = ?
            """;
    public static final String RENT_FIND_BY_CUSTOMER_ID = """
            SELECT r.id as rent_id,
            r.rent_date,
            r.total_amount,
            v.id as vehicle_id,
            v.name as vehicle_brand,
            ri.price
            FROM rent r
            JOIN rent_item ri ON ri.vehicle_id = r.id
            WHERE r.customer_id = ?
            rent BY r.rent_date desc
            """;

    public static final String VEHICLE_DELETE = """
            DELETE FROM vehicle WHERE id = ?
            """;
    public static final String VEHICLE_TOTAL_PAGE_COUNT = """
            SELECT COUNT(*) FROM vehicle
            """;

    public static final String VEHICLE_FIND_BY_CATEGORY_NAME = """
            SELECT v.id as id,
                   v.name as brand,
                   v.model as model,
                   v.year as year,
                   v.rental_price as rental_price,
                   v.price as price,
                   c.id as category_id,
                   c.name as category_name,
                   FROM vehicle v 
                   JOIN category c on c.id = v.category_id
                   WHERE c.name = ?
            """;

    public static final String VEHICLE_FIND_BY_NAME = """
            SELECT * FROM vehicle WHERE name = ?
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
    public static final String CATEGORY_DELETE = """
            DELETE FROM category WHERE id = ?
            """;
    public static final String CATEGORY_FIND_BY_ID = """
            SELECT *  FROM category WHERE id = ?
            """;
    public static final String CATEGORY_FIND_ALL = """
            SELECT *  FROM category
            """;
    public static final String CART_FIND_BY_CUSTOMER_ID = """
            SELECT * FROM cart
            WHERE customer_id = ?
            """;
    public static final String CART_ITEM_FIND_BY_CUSTOMER_ID = """
            SELECT ci.id as id,
            v.id as vehicle_id,
            v.brand as vehicle_brand,
            v.price as vehicle_price
            FROM cart_items ci JOIN cart c ON c.id = ci.cart_id
            JOIN public.vehicle v ON v.id = ci.vehicle_id 
            WHERE c.customer_id = ?
            """;
    public static final String CART_ITEM_SAVE = """
            INSERT INTO cart_items (cart_id, vehicle_id)
            VALUES (?, ?)
            """;

}
