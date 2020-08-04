fun mystery(a, b::bs) =
       if a = b then
          mystery(a, bs)
       else
          b::mystery(a, bs)
      | mystery(a, []) = [];

fun f(g) = g(1);

foldr (fn (x, alist) => x::x::alist) [] (map(fn x => 2 * x) [1,2,3]);
