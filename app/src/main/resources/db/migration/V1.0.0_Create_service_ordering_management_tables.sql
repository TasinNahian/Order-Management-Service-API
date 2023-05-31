-- service_order
CREATE TABLE IF NOT EXISTS public.service_order
(
	cancellation_date timestamp with time zone,
	cancellation_reason character varying(250),
	category character varying(250),
	completion_date timestamp with time zone,
	description character varying(250),	
	expected_completion_date timestamp with time zone,
	external_id character varying(250),
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	notification_contact character varying(250),
	order_date timestamp with time zone,
	priority character varying(250),
	requested_completion_date timestamp with time zone,
	requested_start_date timestamp with time zone,
	start_date timestamp with time zone,	
	state character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250)	
);
-- external_reference 
CREATE TABLE IF NOT EXISTS public.external_reference
(
	external_reference_type character varying(250),
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY(service_order_id) REFERENCES service_order(id)
);
-- service_order_relationship
CREATE TABLE IF NOT EXISTS public.service_order_relationship
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	relationship_type character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id)		
);

--service_order_item
CREATE TABLE IF NOT EXISTS public.service_order_item
(
	action character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	quantity integer,
	state character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id),
	service_order_item character varying(250),
	FOREIGN KEY (service_order_item) REFERENCES service_order_item(id)
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
-- service_order_error_message
CREATE TABLE IF NOT EXISTS public.service_order_error_message
(
	id character varying(250) NOT NULL PRIMARY KEY,
	code character varying(250),
	message character varying(250),
	reason character varying(250),
	reference_error character varying(250),
	status character varying(250),
	timestamp timestamp with time zone,
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id)
);
--service_order_milestone
CREATE TABLE IF NOT EXISTS public.service_order_milestone
(
	description character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	message character varying(250),
	milestone_date character varying(250),
	name character varying(250),
	status character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id)
);
--service_order_jeopardy_alert
CREATE TABLE IF NOT EXISTS public.service_order_jeopardy_alert
(
	alert_date character varying(250),
	exception character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	jeopardy_type character varying(250),
	message character varying(250),
	name character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id)
);

-- service_order_item_ref
CREATE TABLE IF NOT EXISTS public.service_order_item_ref
(
	id character varying(250) NOT NULL PRIMARY KEY,
	item_id character varying(250),
	service_order_href character varying(250),
	service_order_id character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_item_relationship_id character varying(250) UNIQUE,
	FOREIGN KEY (service_order_item_relationship_id) REFERENCES service_order_item_relationship(id),
	service_order_error_message_id character varying(250),
	FOREIGN KEY (service_order_error_message_id) REFERENCES service_order_error_message(id),
	service_order_milestone_id character varying(250),
	FOREIGN KEY (service_order_milestone_id) REFERENCES service_order_milestone(id),
	service_order_jeopardy_alert_id character varying(250),
	FOREIGN KEY (service_order_jeopardy_alert_id) REFERENCES service_order_jeopardy_alert(id)

);
--service_order_item_error_message
CREATE TABLE IF NOT EXISTS public.service_order_item_error_message
(
	id character varying(250) NOT NULL PRIMARY KEY,
	code character varying(250),
	message character varying(250),
	reason character varying(250),
	reference_error character varying(250),
	status character varying(250),
	timestamp timestamp with time zone,
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_item_id character varying(250),
	FOREIGN KEY (service_order_item_id) REFERENCES service_order_item(id)
);
-- appointment_ref
CREATE TABLE IF NOT EXISTS public.appointment_ref
(
	description character varying(250),
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_item_id character varying(250) UNIQUE,
	FOREIGN KEY (service_order_item_id) REFERENCES service_order_item(id)

);

--service_ref_or_value
CREATE TABLE IF NOT EXISTS public.service_ref_or_value
(
	category character varying(250),
	description character varying(250),
	end_date character varying(250),
	has_started BOOLEAN,
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
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
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_item_id character varying(250) UNIQUE,
	FOREIGN KEY (service_order_item_id) REFERENCES service_order_item(id),
	supporting_service character varying(250),
	FOREIGN KEY (supporting_service) REFERENCES service_ref_or_value(id)
);


-- Note
CREATE TABLE IF NOT EXISTS public.note
(
	author character varying(250),
	date timestamp with time zone,
	id character varying(250) NOT NULL PRIMARY KEY,
	text character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);

--service_relationship
CREATE TABLE IF NOT EXISTS public.service_relationship
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	relationship_type character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);
ALTER TABLE service_ref_or_value ADD COLUMN service_relationship_id character varying(250);
ALTER TABLE service_ref_or_value ADD CONSTRAINT service_relationship_id FOREIGN KEY (service_relationship_id) REFERENCES service_ref_or_value(id);


--related_party
CREATE TABLE IF NOT EXISTS public.related_party
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	role character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_order_id character varying(250),
	FOREIGN KEY (service_order_id) REFERENCES service_order(id),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);
--feature
CREATE TABLE IF NOT EXISTS public.feature
(
	id character varying(250) NOT NULL PRIMARY KEY,
	is_bundle BOOLEAN,
	is_enabled BOOLEAN,
	name character varying(250),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);

--valid_for
CREATE TABLE IF NOT EXISTS public.valid_for
(
	id character varying(250) NOT NULL PRIMARY KEY,
	start_date_time character varying(250),
	end_date_time character varying(250)
);
--feature_relationship
CREATE TABLE IF NOT EXISTS public.feature_relationship
(
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	relationship_type character varying(250),
	feature_id character varying(250),
	FOREIGN KEY(feature_id) REFERENCES feature(id),
	valid_for_id character varying(250),
	FOREIGN KEY(valid_for_id) REFERENCES valid_for(id)
);
--constraint
CREATE TABLE IF NOT EXISTS public.constraint_ref
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	version character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	feature_id character varying(250),
	FOREIGN KEY(feature_id) REFERENCES feature(id)
);


--characteristic
CREATE TABLE IF NOT EXISTS public.characteristic
(
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	value character varying(250),
	value_type character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	feature_id character varying(250) UNIQUE,
	FOREIGN KEY(feature_id) REFERENCES feature(id),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY(service_ref_or_value_id) REFERENCES service_ref_or_value(id),
	service_relationship_id character varying(250),
	FOREIGN KEY (service_relationship_id) REFERENCES service_relationship(id)
);
--characteristic_relationship
CREATE TABLE IF NOT EXISTS public.characteristic_relationship
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	relationship_type character varying(250),
	base_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	characteristic_id character varying(250),
	FOREIGN KEY (characteristic_id) REFERENCES characteristic(id)
);

--related_place_ref_or_value
CREATE TABLE IF NOT EXISTS public.related_place_ref_or_value
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	role character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);
--related_entity_ref_or_value
CREATE TABLE IF NOT EXISTS public.related_entity_ref_or_value
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	role character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);

--related_service_order_item
CREATE TABLE IF NOT EXISTS public.related_service_order_item
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	item_action character varying(250),
	item_id character varying(250),
	role character varying(250),
	service_order_href character varying(250),
	service_order_id character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);

--resource_ref
CREATE TABLE IF NOT EXISTS public.resource_ref
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_ref_or_value_id character varying(250),
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);

--service_specification_ref
CREATE TABLE IF NOT EXISTS public.service_specification_ref
(
	href character varying(250),
	id character varying(250) NOT NULL PRIMARY KEY,
	name character varying(250),
	version character varying(250),
	base_type character varying(250),
	referred_type character varying(250),
	schema_location character varying(250),
	type character varying(250),
	service_ref_or_value_id character varying(250) UNIQUE,
	FOREIGN KEY (service_ref_or_value_id) REFERENCES service_ref_or_value(id)
);





