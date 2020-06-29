from pyspark.shell import spark

df = spark.read.csv("test_data.csv", sep=" ", header=True)
df = df.select(df["one"] - df["two"])
df.show()
