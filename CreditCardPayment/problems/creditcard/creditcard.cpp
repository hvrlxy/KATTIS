#include <iostream>

using namespace std;

const double eps = 1e-8;
const double max_b = 50000;
const int max_payments = 1200;

int main()
{
	int tn;
	for (cin >> tn; tn--;)
	{
		double r, b, m;
		cin >> r >> b >> m;
				
		int minnum = 0;
		double pb = max_b + 1;
		while (b > 0 && minnum++ <= max_payments && b < pb)
		{
			pb = b;
			b *= (1 + r / 100.0);
			b = (int)(b * 100 + 0.5 + eps) / 100.0;
			b -= m;
		}
		if (minnum > max_payments || b >= pb)
			cout << "impossible" << endl;
		else
			cout << minnum << endl;
	}
		
	return 0;
}
