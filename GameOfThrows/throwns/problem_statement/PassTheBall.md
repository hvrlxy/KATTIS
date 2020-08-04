Game of Stones
==============

Daenerys frequently invents games to help teach her 2nd grade Computer Science class about various data structures. For this week's lesson she has the children form a circle and (carefully) toss around a petrified dragon egg.

The _n_ children are numbered from 0 to _n_ - 1 (it is a Comptuer Science class after all) clockwise around the circle. Child 0 always starts with the egg.

TODO: Explain undo and the fact that a subsequent undo isn't an undo of the undo, just a further undo. Wheee.

Input
-----
Input consists of two lines. The first line contains integers 1 &le; _n_ &le; 30 and 1 &le; _k_ &le; 100 indicating the number of students and how many times Daenerys has the children toss the egg, respectively. The following line contains _k_ toss descriptions _t_<sub>_i_</sub> for 0 &le; _i_ &le; _k_-1. Each toss description _t_<sub>_i_</sub> is either an integer -10,000 &le; _p_ &le; 10,000 indicating how many positions to toss the ball clockwise or `UNDOu` [John: I can't get "Undo" and "_u_" to touch in markdown] indicating that the last 1 &le; _u_ &le; _i_ throws should be undone.

Output
------
Display the number of the child with the egg at the end of the game.

| Sample Input 1              | Sample Output 1 |
| --------------------------- | --------------- |
| `5 4`<br />`8 -2 3 UNDO2`   | `3`             |
