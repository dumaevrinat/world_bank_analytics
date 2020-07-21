import pandas as pd
from statsmodels.tsa.vector_ar.var_model import VAR
import psycopg2


def var(data):
    n_years = 5
    model = VAR(data.to_numpy().tolist())
    results = model.fit()
    return results.forecast(results.endog, steps=n_years)[:, 0]


conn = psycopg2.connect(host="192.168.70.202", port=5432, database="wba", user="wba", password="wba_password")
cur = conn.cursor()

ep = pd.read_sql_query("SELECT country_name, time, gdp_growth_annual FROM economic_policy", conn)
fs = pd.read_sql_query("SELECT country_name, time, inflation_consumer_prices_annual FROM fin_sector", conn)
sp = pd.read_sql_query(
    "SELECT country_name, time, unemployment_total__of_total_labor_force__modeled_ilo_estimate FROM social_protection",
    conn)

fs["inflation_growth_rate"] = fs["inflation_consumer_prices_annual"].diff()
fs = fs.drop("inflation_consumer_prices_annual", axis=1)

sp["unemployment_growth_rate"] = sp["unemployment_total__of_total_labor_force__modeled_ilo_estimate"].diff()
sp = sp.drop("unemployment_total__of_total_labor_force__modeled_ilo_estimate", axis=1)

combined = ep.merge(fs, left_on=["country_name", "time"], right_on=["country_name", "time"])
combined = combined.merge(sp, left_on=["country_name", "time"], right_on=["country_name", "time"])
combined = combined.dropna()

cur.execute("DELETE FROM economic_policy_prediction")  # delete previous data

countries = combined["country_name"].unique()
for c in countries:
    part = combined.loc[combined["country_name"] == c]
    if len(part) < 15:  # not enough data
        continue
    try:
        res = var(part[["gdp_growth_annual", "inflation_growth_rate", "unemployment_growth_rate"]])
    except Exception as e:
        repr(e)
        continue
    last_year = int(part["time"].tail(1).item())
    years = range(last_year + 1, last_year + 6)
    for time, gdp in zip(years, res):
        cur.execute(
            "INSERT INTO economic_policy_prediction (country_name, time, gdp_growth_annual) VALUES (%s, %s, %s)",
            (c, time, gdp))

conn.commit()
cur.close()
conn.close()
