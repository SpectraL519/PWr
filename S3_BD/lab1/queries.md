#  Lab 1: queries

* [Sakila database download](https://dev.mysql.com/doc/index-other.html)

* [Sakila database structure](https://dev.mysql.com/doc/sakila/en/sakila-structure.html)

<br />
<br />

### **Task 1:**
```
SHOW TABLES;
```
```
SHOW FULL TABLES;
```

<br />

### **Task 2:**
```
SELECT title FROM film WHERE length > 120;
```

<br />

### **Task 3:**
```
SELECT titile FROM film WHERE rating='G' ORDER BY length LIMIT 4;
```

<br />

### **Task 4:**
```
SELECT f.title, l.name 
    FROM film AS f JOIN language AS l ON f.language_id=l.language_id
    WHERE f.description LIKE "%Drama%";
```

<br />

### **Task 5:**
```
SELECT f.title 
    FROM film AS f JOIN film_category AS fc ON f.film_id=fc.film_id
    JOIN category AS c ON fc.category_id=c.category_id
    WHERE c.name='Family' AND f.description LIKE '%Documentary%';
```

<br />

### **Task 6:**
```
SELECT f.title
    FROM film AS f JOIN film_category AS fc ON f.film_id=fc.film_id
    JOIN category AS c ON fc.category_id=c.category_id
    WHERE c.name='Children' AND f.rating<>'G';
```

<br />

### **Task 7:**
```
SELECT rating, COUNT(film_id) FROM film GROUP BY rating;
```

<br />

### **Task 8:**
```
SELECT DISTINCT f.title
    FROM film AS f JOIN inventory AS i ON f.film_id=i.film_id
    JOIN rental AS r ON i.inventory_id=r.inventory_id
    WHERE DATE(r.rental_date) BETWEEN '2005-05-31' AND '2005-06-16'
    ORDER BY f.title;
```

<br />

### **Task 9:**
```
SELECT DISTINCT a.first_name, a.last_name 
    FROM actor AS a JOIN film_actor AS fa ON a.actor_id=fa.actor_id
    JOIN film AS f ON fa.film_id=f.film_id
    WHERE f.special_features LIKE "%Deleted Scenes%";
```

<br />

### **Task 10:**
```
SELECT DISTINCT c.first_name, c.last_name
    FROM customer AS c JOIN rental AS r ON c.customer_id=r.customer_id
    JOIN payment AS p ON r.rental_id=p.rental_id
    WHERE r.staff_id<>p.staff_id;
```

<br />

### **Task 11:**
```
SELECT c.first_name, c.last_name, COUNT(r.rental_id)
    FROM customer AS c JOIN rental AS r ON c.customer_id=r.customer_id
    GROUP BY c.customer_id
    HAVING COUNT(r.rental_id) >
        (SELECT COUNT(r.rental_id)
        FROM rental AS r JOIN customer AS c ON r.customer_id=c.customer_id
        WHERE c.email='MARY.SMITH@sakilacustomer.org');
```

<br />

### **Task 12:**
```
SELECT a1.first_name, a1.last_name, a2.first_name, a2.last_name
    FROM
        (SELECT fa1.actor_id AS actor_1, fa2.actor_id AS actor_2
            FROM film_actor AS fa1, film_actor AS fa2
            WHERE fa1.film_id=fa2.film_id AND fa1.actor_id<fa2.actor_id
            GROUP BY fa1.actor_id, fa2.actor_id
            HAVING COUNT(*)>1) AS ap
        JOIN actor AS a1 ON ap.actor_1=a1.actor_id
        JOIN actor AS a2 ON ap.actor_2=a2.actor_id;
```

<br />

### **Task 13:**
```
SELECT a.last_name
    FROM actor AS a WHERE a.actor_id NOT IN
        (SELECT fa.actor_id
            FROM film_actor AS fa JOIN film AS f ON fa.film_id=f.film_id
            WHERE f.title LIKE 'C%');
```

<br />

### **Task 14:**
```
SELECT a.last_name
    FROM actor AS a
    WHERE
        (SELECT COUNT(*)
            FROM film_actor AS fa 
            JOIN film_category AS fc ON fa.film_id=fc.film_id
            JOIN category AS c ON fc.category_id=c.category_id
            WHERE a.actor_id=fa.actor_id AND c.name='Action') > 
        (SELECT COUNT(*)
            FROM film_actor AS fa 
            JOIN film_category AS fc ON fa.film_id=fc.film_id
            JOIN category AS c ON fc.category_id=c.category_id
            WHERE a.actor_id=fa.actor_id AND c.name='Horror');
```

<br />

### **Task 15:**
```
SELECT DISTINCT c.email
    FROM customer AS c JOIN payment AS p ON c.customer_id=p.customer_id
    GROUP BY c.email
    HAVING AVG(p.amount) <
        (SELECT AVG(amount) FROM payment 
            WHERE DATE(payment_date)='2005-07-30');
```

<br />

### **Task 16:**
```
UPDATE film AS f SET f.language_id=
    (SELECT language_id FROM language
        WHERE name='Italian')
    WHERE f.title='YOUNG LANGUAGE';
```

<br />

### **Task 17:**
```
INSERT INTO language(name) VALUES('Spanish');
```
```
UPDATE film AS f SET f.language_id=
    (SELECT language_id FROM language
        WHERE name='Spanish')
    WHERE f.film_id IN
        (SELECT fa.film_id
            FROM film_actor AS fa JOIN actor AS a ON fa.actor_id=a.actor_id
            WHERE a.first_name='ED' AND a.last_name='CHASE');
```

<br />

### **Task 18:**
```
ALTER TABLE language ADD films_no int;
```
```
UPDATE language AS l SET l.films_no=
    (SELECT COUNT(*) FROM film AS f
        WHERE f.language_id=l.language_id);
```

<br />

### **Task 19:**
```
ALTER TABLE film DROP COLUMN release_year;
```
