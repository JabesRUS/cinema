CREATE TABLE movie (id serial PRIMARY KEY,
					name varchar(100),
					description varchar(200));

CREATE TABLE place (id serial PRIMARY KEY,
					name varchar(5)
					);
					
CREATE TABLE "session" (id serial PRIMARY KEY,
						movie_id int REFERENCES movie(id),
						time varchar(10),
						price numeric(10, 2));
						
CREATE TABLE ticket (id serial PRIMARY KEY,
					plase_id int REFERENCES place(id),
					session_id int REFERENCES session(id),
					is_bought BOOLEAN NOT NULL);




