import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt

# import the dataset
data = pd.read_csv('GestionEmpresarial\\python\\KagglePenalties\\WorldCupShootouts.csv')
print(data)
print(type(data))

# get a table with the number of goals and on target shots by zone
data_zones = data.groupby(['Zone']).agg({'OnTarget': 'sum', 'Goal': 'sum'})
print(data_zones)

# efficiency by zone
data_zones['Efficiency'] = data_zones['Goal'] / data_zones['OnTarget'] * 100
print(data_zones)

# plot the efficiency by zone as a heatmap using the zones as areas of the plot
data_zones_efficiency = data_zones['Efficiency']
data_zones_efficiency = data_zones_efficiency.to_numpy().reshape(3,3)
print(data_zones_efficiency)


# create the heatmap and show it
sns.heatmap(data_zones_efficiency, vmin=60, vmax=100, annot=True, cmap='Oranges_r', xticklabels=['Left', 'Center', 'Right'], yticklabels=['Top', 'Center', 'Bottom'], fmt=".2f")
plt.show()






