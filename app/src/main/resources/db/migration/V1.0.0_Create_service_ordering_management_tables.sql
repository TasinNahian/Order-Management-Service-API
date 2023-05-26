-- service_order
CREATE TABLE IF NOT EXISTS public.service_order
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	category character varying(250),
	completion_date timestamp with time zone,
	description character varying(250),
	expected_completion_date timestamp with time zone,
	external_id character varying(250),
	notification_contact character varying(250),
	order_date timestamp with time zone,
	priority character varying(250),
	requested_start_date timestamp with time zone,
	cancellation_date timestamp with time zone,
	cancellation_reason character varying(250),
	state character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250)	
);

--service_order_item
CREATE TABLE IF NOT EXISTS public.service_order_item
(
	id character varying(250) NOT NULL PRIMARY KEY,
	quantity integer,
	action character varying(250),
	state character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id)	
);
--service
CREATE TABLE IF NOT EXISTS public.service
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	category character varying(250),
	description character varying(250),
	end_date character varying(250),
	has_started BOOLEAN,
	is_bundle BOOLEAN,
	is_service_enabled BOOLEAN,
	is_stateful BOOLEAN,
	name character varying(250),
	service_date character varying(250),
	service_type character varying(250),
	start_date timestamp with time zone,
	start_mode character varying(250),
	state character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	referred_type character varying(250),
	service_order_item_id character varying(250),
	FOREIGN KEY (service_order_item_id) REFERENCES service_order_item(id)
);
-- Note 
CREATE TABLE IF NOT EXISTS public.note
(
	id character varying(250) NOT NULL PRIMARY KEY,
	author character varying(250),
	date timestamp with time zone,
	system character varying(250),
	text character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id),
	service_id character varying(250),
	FOREIGN KEY (service_id) REFERENCES service(id)
);



-- service_order_item_relationship 
CREATE TABLE IF NOT EXISTS public.service_order_item_relationship
(
	id character varying(250) NOT NULL PRIMARY KEY,
	relationship_type character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_item_id character varying(250),
	FOREIGN KEY (service_order_item_id) REFERENCES service_order_item(id)
);
-- order_item
CREATE TABLE IF NOT EXISTS public.order_item
(
	item_id character varying(250) NOT NULL PRIMARY KEY,
	service_order_href character varying(250),
	service_order_id character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_item_relationship_id character varying(250),
	FOREIGN KEY (service_order_item_relationship_id) REFERENCES service_order_item_relationship(id)
);


-- appointment_ref
CREATE TABLE IF NOT EXISTS public.appointment_ref
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	description character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	referred_type character varying(250),
	service_order_item_id character varying(250) UNIQUE,
	FOREIGN KEY (service_order_item_id) REFERENCES service_order_item(id)

);

--feature need
CREATE TABLE IF NOT EXISTS public.feature
(
	id character varying(250) NOT NULL PRIMARY KEY,
	is_bundle BOOLEAN,
	is_enabled BOOLEAN,
	name character varying(250),
	service_id character varying(250),
	FOREIGN KEY (service_id) REFERENCES service(id)
);

--service_relationship 
CREATE TABLE IF NOT EXISTS public.service_relationship
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	relationship_type character varying(250),
	service character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_id character varying(250),
	FOREIGN KEY(service_id) REFERENCES service(id)
);

--characteristic 
CREATE TABLE IF NOT EXISTS public.characteristic
(
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	value_type character varying(250),
	value character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	feature_id character varying(250),
	FOREIGN KEY(feature_id) REFERENCES feature(id),
	service_id character varying(250),
	FOREIGN KEY(service_id) REFERENCES service(id),
	service_relationship_id character varying(250),
	FOREIGN KEY (service_relationship_id) REFERENCES service_relationship(id)
);
-- service_order_relationship or characteristic_relationship
CREATE TABLE IF NOT EXISTS public.service_order_relationship
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	relationship_type character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	referred_type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id),
	characteristic_id character varying(250),
	FOREIGN KEY (characteristic_id) REFERENCES characteristic(id)
		
);

--place
CREATE TABLE IF NOT EXISTS public.place
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	name character varying(250),
	role character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_id character varying(250),
	FOREIGN KEY (service_id) REFERENCES service(id)
);

--related_party need
CREATE TABLE IF NOT EXISTS public.related_party
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	name character varying(250),
	role character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	referred_type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id),
	service_id character varying(250),
	FOREIGN KEY (service_id) REFERENCES service(id)
);

--service_specification_ref/ constraint need
CREATE TABLE IF NOT EXISTS public.service_specification_ref
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	name character varying(250),
	version character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	referred_type character varying(250),
	feature_id character varying(250),
	FOREIGN KEY(feature_id) REFERENCES feature(id),
	service_id character varying(250),
	FOREIGN KEY(service_id) REFERENCES service(id)
);

--resource_ref need
CREATE TABLE IF NOT EXISTS public.resource_ref
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	name character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	referred_type character varying(250),
	service_id character varying(250),
	FOREIGN KEY(service_id) REFERENCES service(id)
);
--supporting_service need
CREATE TABLE IF NOT EXISTS public.supporting_service
(
	id character varying(250) NOT NULL PRIMARY KEY,
	supporting_service character varying(250),
	service_id character varying(250),
	FOREIGN KEY(service_id) REFERENCES service(id),
	service_order_item_id character varying(250),
	FOREIGN KEY(service_order_item_id) REFERENCES service_order_item(id),
	service_order_id character varying(250),
	FOREIGN KEY(service_order_id) REFERENCES service_order(id)
);

-- external reference need
CREATE TABLE IF NOT EXISTS public.external_reference
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	external_reference_type character varying(250),
	name character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY(service_order_id) REFERENCES service_order(id)
);

-- error_message need
CREATE TABLE IF NOT EXISTS public.error_message
(
	id character varying(250) NOT NULL PRIMARY KEY,
	code character varying(250),
	message character varying(250),
	reason character varying(250),
	referenceError character varying(250),
	status character varying(250),
	timestamp timestamp with time zone,
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_item_id character varying(250),
	FOREIGN KEY (service_order_item_id) REFERENCES service_order_item(id)
);


--feature_relationship
CREATE TABLE IF NOT EXISTS public.feature_relationship
(
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	relationship_type character varying(250),
	feature_id character varying(250),
	FOREIGN KEY(feature_id) REFERENCES feature(id)
	
);

--valid_for
CREATE TABLE IF NOT EXISTS public.valid_for
(
	id character varying(250) NOT NULL PRIMARY KEY,
	end_date_time timestamp with time zone,
	start_date_time timestamp with time zone,
	feature_relationship_id character varying(250) UNIQUE,
	FOREIGN KEY (feature_relationship_id) REFERENCES feature_relationship(id)
);
--service_order_item
CREATE TABLE IF NOT EXISTS public.item
(
	id character varying(250) NOT NULL PRIMARY KEY,
	href character varying(250),
	item_id character varying(250),
	role character varying(250),
	service_order_href character varying(250),
	service_order_id character varying(250),
	item_action character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	referred_type character varying(250),
	service_id character varying(250),
	FOREIGN KEY (service_id) REFERENCES service(id)
);




