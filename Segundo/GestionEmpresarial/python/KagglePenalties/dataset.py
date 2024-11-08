import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt

#* STEP 1: Import the dataset

# import the dataset
data = pd.read_csv('GestionEmpresarial\\python\\KagglePenalties\\WorldCupShootouts.csv')
# prints to see if the data was imported correctly
print(data)
print(type(data))

#* STEP 2: Data Preprocessing 

# get a table with the number of goals and on target shots by zone
data_zones = data.groupby(['Zone']).agg({'OnTarget': 'sum', 'Goal': 'sum'})
# prints the table to see if the data was grouped correctly
print(data_zones)

#* SETP 2.1: Data Preprocessing - Efficiency by zone

# efficiency by zone
data_zones['Efficiency'] = data_zones['Goal'] / data_zones['OnTarget'] * 100
# prints the table to see if the efficiency was calculated correctly
print(data_zones)

# plot the efficiency by zone as a heatmap using the zones as areas of the plot
data_zones_efficiency = data_zones['Efficiency']
# reshapes the data to a 3x3 matrix
data_zones_efficiency = data_zones_efficiency.to_numpy().reshape(3,3)
# prints the reshaped data to see if it was done correctly
print(data_zones_efficiency)

#* STEP 3: Data Visualization heatmap of efficiency by zone

# create the heatmap and show it
# using the vmin and vmax parameters to set the range of the color scale
# using the annot parameter to show the values of the cells
# using the cmap parameter to set the color map with the '_r' to reverse the color scale
# using the xticklabels and yticklabels parameters to set the labels of the axes
# using the fmt parameter to set the format of the values in the cells to get 100 instead of 1e+02 shows the values as percentages
sns.heatmap(data_zones_efficiency, vmin=60, vmax=100, annot=True, cmap='Oranges_r', xticklabels=['Left', 'Center', 'Right'], yticklabels=['Top', 'Center', 'Bottom'], fmt=".2f", cbar_kws={'label': 'Efficiency (%)'})
# using the title function to set the title of the plot
plt.title('Efficiency by Zone')
# using the show function to show the plot
plt.show()

#* STEP 4: Data Visualization heatmap of penalties by zone using %

# heatmap of penalties by zone
data_zones_penalties = data_zones['OnTarget']
# reshapes the data to a 3x3 matrix
data_zones_penalties = data_zones_penalties.to_numpy().reshape(3,3)
data_zones_penalties = data_zones_penalties / data_zones_penalties.sum() * 100
# prints the reshaped data to see if it was done correctly
print(data_zones_penalties)

#* STEP 5: Data Visualization heatmap of penalties by zone

# create the heatmap and show it 
# using the annot parameter to show the values of the cells
# using the cmap parameter to set the color map
# using the xticklabels and yticklabels parameters to set the labels of the axes
sns.heatmap(data_zones_penalties, annot=True, cmap='Blues', xticklabels=['Left', 'Center', 'Right'], yticklabels=['Top', 'Center', 'Bottom'], fmt=".2f", cbar_kws={'label': 'Penalties (%)'})
# using the title function to set the title of the plot
plt.title('Penalties by Zone')
# using the show function to show the plot
plt.show()

#* STEP 6: Data Visualization bar plot of efficiency by penalty number

# get a table with the number of goals and on target shots by penalty number
data_penalties = data.groupby(['Penalty_Number']).agg({'Goal': 'sum', 'OnTarget': 'sum'})
# get the efficiency by penalty number
data_penalties['Efficiency'] = data_penalties['Goal'] / data_penalties['OnTarget'] * 100
print(data_penalties)
# gets the table with the efficiency by penalty number
data_penalties_efficiency = data_penalties['Efficiency']

#* STEP 7: Data Visualization bar plot of efficiency by penalty number

# set the figure size to avoid overlapping values
plt.figure(figsize=(12, 7))

# creates a bar plot with the efficiency by penalty number
ax = data_penalties_efficiency.plot(kind='bar', color='skyblue', edgecolor='black', width=0.8)
# sets the labels of the axes and the title of the plot
plt.ylabel('Efficiency (%)')
plt.xlabel('Penalty Number')
plt.title('Efficiency by Penalty Number')

# add value labels on top of each bar
for p in ax.patches:
    ax.annotate(f'{p.get_height():.2f}%', (p.get_x() + p.get_width() / 2., p.get_height()), 
                ha='center', va='center', xytext=(0, 10), textcoords='offset points')

# shows the plot
plt.show()











