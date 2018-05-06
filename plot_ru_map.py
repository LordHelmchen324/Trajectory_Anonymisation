import sys
import matplotlib.pyplot as plt

n = len(sys.argv) / 2
disclosure_risks = []
information_losses = []

for i in range(n) :
    dr = float(sys.argv[1 + i * 2])
    disclosure_risks.append(dr)

    il = float(sys.argv[1 + i * 2 + 1])
    information_losses.append(il)

plt.axis((0, 100, 0, 100))
plt.scatter(x = disclosure_risks, y = information_losses);
plt.show()

# python plot_ru_map.py 50.0 25.0 25.0 42.0