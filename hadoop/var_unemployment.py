import pandas as pd
from statsmodels.tsa.vector_ar.var_model import VAR
from sklearn.metrics import mean_squared_error
# import matplotlib.pyplot as plt


def var(data):
    n_years = 5
    model = VAR(data.iloc[:-n_years].to_numpy().tolist())
    # model = VAR(data)
    results = model.fit()
    pred = results.forecast(results.endog, steps=n_years)

    data1 = data.tail(n_years)["gdp_growth_annual"].tolist()
    pred1 = [row[0] for row in pred]

    # plot_data = data["gdp_growth_annual"].tolist()
    # plt.plot(range(len(plot_data)), plot_data)
    # plt.plot(range(len(plot_data) - n_years, len(plot_data)), pred1)
    # plt.show()

    return mean_squared_error(data1, pred1)


pd.set_option('display.expand_frame_repr', False)
pd.set_option('display.max_columns', None)
pd.set_option('display.max_rows', None)

ep = pd.read_csv("data/economic_policy_202007011934.csv")
ep = ep[["country_name", "time", "gdp_growth_annual"]]
fs = pd.read_csv("data/fin_sector_202007031526.csv")
fs = fs[["country_name", "time", "inflation_consumer_prices_annual"]]
sp = pd.read_csv("data/social_protection_202007031525.csv")
sp = sp[["country_name", "time", "unemployment_total__of_total_labor_force__modeled_ilo_estimate"]]


fs["inflation_growth_rate"] = fs["inflation_consumer_prices_annual"].diff()
fs = fs.drop("inflation_consumer_prices_annual", axis=1)

sp["unemployment_growth_rate"] = sp["unemployment_total__of_total_labor_force__modeled_ilo_estimate"].diff()
sp = sp.drop("unemployment_total__of_total_labor_force__modeled_ilo_estimate", axis=1)

combined = ep.merge(fs, left_on=["country_name", "time"], right_on=["country_name", "time"])
combined = combined.merge(sp, left_on=["country_name", "time"], right_on=["country_name", "time"])
combined = combined.dropna()

countries = combined["country_name"].unique()
mean = 0
# for c in ["World"]:
for c in countries:
    part = combined.loc[combined["country_name"] == c]
    part = part[["gdp_growth_annual", "inflation_growth_rate", "unemployment_growth_rate"]]
    if len(part) < 15:
        continue
    try:
        res = var(part)
    except Exception as e:
        repr(e)
        continue
    print(c, res)
    mean += res

print("mean", mean / len(countries))
