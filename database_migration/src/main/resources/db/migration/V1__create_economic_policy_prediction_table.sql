create table economic_policy_prediction (
    id serial primary key,
    country_name varchar(100),
    time integer,
    gdp_growth_annual real
);
