CREATE DATABASE "pizza_delivery"
  WITH OWNER "postgres"
  ENCODING 'UTF8'
  LC_COLLATE = 'ru_RU.UTF-8'
  LC_CTYPE = 'ru_RU.UTF-8'
  TEMPLATE = template0;

CREATE SEQUENCE pk_id_address;
CREATE SEQUENCE pk_id_authority;
CREATE SEQUENCE pk_id_card;
CREATE SEQUENCE pk_id_customer;
CREATE SEQUENCE pk_id_order;
CREATE SEQUENCE pk_id_pizza;
CREATE SEQUENCE pk_id_user;

CREATE TABLE addresses (
   id bigint DEFAULT nextval('pk_id_address') PRIMARY KEY,
   city varchar(100),
   street varchar(100), 
   apartment varchar(25)
);
   
CREATE TABLE cards (
   id bigint DEFAULT nextval('pk_id_card') PRIMARY KEY,
   points integer(10)
);
   
CREATE TABLE pizzas (
   id bigint DEFAULT nextval('pk_id_pizza') PRIMARY KEY,
   name varchar(100) NOT NULL,
   type varchar(100) NOT NULL,
   price numeric(10,2) NOT NULL
);

CREATE TABLE customers (
   id bigint DEFAULT nextval('pk_id_customer') PRIMARY KEY, 
   name varchar(100), 
   address_id bigint,
   card_id bigint
);   
ALTER TABLE "public"."customers" 
ADD CONSTRAINT "fk_address" FOREIGN KEY ("address_id") REFERENCES "public"."addresses"("id"), 
ADD CONSTRAINT "fk_card" FOREIGN KEY ("card_id") REFERENCES "public"."cards"("id");

CREATE TABLE users (
   id bigint DEFAULT nextval('pk_id_user') PRIMARY KEY,
   active boolean NOT NULL, 
   login  varchar(25) NOT NULL, 
   password varchar(120) NOT NULL,
   customer_id bigint
), UNIQUE ("login");
ALTER TABLE "public"."users" 
ADD CONSTRAINT "fk_customer" FOREIGN KEY ("customer_id") REFERENCES "public"."customers"("id");
   
CREATE TABLE orders (
   id bigint DEFAULT nextval('pk_id_order') PRIMARY KEY,
   price numeric,
   order_state varchar(25),
   customer_id bigint,
   address_id bigint
);
ALTER TABLE "public"."orders" 
ADD CONSTRAINT "fk_customer" FOREIGN KEY ("customer_id") REFERENCES "public"."customers"("id"), 				  
ADD CONSTRAINT "fk_address" FOREIGN KEY ("address_id") REFERENCES "public"."addresses"("id");
 
CREATE TABLE order_pizza ( 
   order_id bigint NOT NULL, 
   pizza_id bigint NOT NULL,
   pizzas_quantity integer(2),
   price numeric,
   PRIMARY KEY("pizza_id", "order_id")
);
ALTER TABLE "public"."order_pizza" 
ADD CONSTRAINT "fk_pizza" FOREIGN KEY ("pizza_id") REFERENCES "public"."pizzas"("id"), 
ADD CONSTRAINT "fk_order" FOREIGN KEY ("order_id") REFERENCES "public"."orders"("id");
  
CREATE TABLE authorities (
	id bigint DEFAULT nextval('pk_id_authority') PRIMARY KEY, 
    authority varchar(25) NOT NULL
);
                             
CREATE TABLE user_authority ( 
   user_id bigint NOT NULL, 
   authority_id bigint NOT NULL,
   PRIMARY KEY("user_id", "authority_id")
);  
ALTER TABLE "public"."user_authority" 
ADD CONSTRAINT "fk_user" FOREIGN KEY ("user_id") REFERENCES "public"."users"("id"), 
ADD CONSTRAINT "fk_authority" FOREIGN KEY ("authority_id") REFERENCES "public"."authorities"("id");
   
INSERT INTO "public"."authorities"("authority") VALUES('ROLE_USER');
INSERT INTO "public"."authorities"("authority") VALUES('ROLE_ADMIN');
INSERT INTO "public"."users"("login", "password") VALUES('admin', '$2a$10$sCiXFLlJzJv2xgVecjBSZeqU7G7WELxUbKGQDBvCrqMGMwnX52Cma');